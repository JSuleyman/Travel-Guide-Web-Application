package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.TravelDestinationStatusCountRequestDTO;
import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.DTO.response.PlacesToVisitByValueResponseDTO;
import com.example.travelguidewebapplication.DTO.response.UserCreatedListResponseDTO;
import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.exception.MandatoryException;
import com.example.travelguidewebapplication.model.*;
import com.example.travelguidewebapplication.repository.StorageRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationCardIconListRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.repository.TravelPlaceCategoryRepository;
import com.example.travelguidewebapplication.service.inter.TravelDestinationCardIconListService;
import com.example.travelguidewebapplication.service.inter.TravelDestinationDetailsService;
import com.example.travelguidewebapplication.service.inter.TravelDestinationService;
import com.example.travelguidewebapplication.service.inter.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelDestinationServiceImpl implements TravelDestinationService {

    private final TravelDestinationRepository travelDestinationRepository;
    private final TravelPlaceCategoryRepository placeKeyRepository;
    private final UserService userService;
    private final TravelDestinationDetailsService travelDestinationDetailsService;
    private final StorageRepository storageRepository;
    private final TravelDestinationCardIconListRepository cardIconListRepository;
    private final TravelDestinationCardIconListService cardIconListService;

    @Override
    @Transactional
    public List<PlacesToVisitByValueResponseDTO> getByValue(String key) {
        List<PlacesToVisitByValueResponseDTO> responseDTOs = new ArrayList<>();
        List<TravelDestination> travelDestinations = travelDestinationRepository.findByTravelPlaceKeyValue(key, Status.COMPLETED);

        for (TravelDestination travelDestination : travelDestinations) {
            String imgName = getImageNameForTravelDestination(travelDestination);
            PlacesToVisitByValueResponseDTO responseDTO = mapToPlacesToVisitByValueResponseDTO(travelDestination, imgName);
            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }

    @Override
    @Transactional
    public List<UserCreatedListResponseDTO> createdByUserList(String status) {
        Integer userId = getCurrentUserId();
        Status statusEnum = Status.fromValue(status);
        List<TravelDestination> travelDestinations = travelDestinationRepository.createdByUserList(userId, statusEnum);
        return travelDestinations.stream()
                .map(this::mapToUserCreatedListResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String createUserCustomCard(UserCustomCardRequestDTO customCardRequestDTO) {
        validateCustomCardRequest(customCardRequestDTO);
        User user = userService.getCurrentUser();
        TravelPlaceCategory placeCategory = placeKeyRepository.findTravelPlaceKeyByKey(customCardRequestDTO.getCategoryId());
        TravelDestination savedTravelDestination = createTravelDestination(customCardRequestDTO, user, placeCategory);
        createTravelDestinationDetails(customCardRequestDTO, savedTravelDestination);
        createTravelDestinationCardIconList(customCardRequestDTO, savedTravelDestination);
        return savedTravelDestination.getId();
    }

    @Override
    public TravelDestinationStatusCountRequestDTO statusCount() {
        Integer userId = getCurrentUserId();
        Integer pendingCount = getCountByStatus(userId, Status.PENDING);
        Integer approvedCount = getCountByStatus(userId, Status.APPROVED);
        Integer completedCount = getCountByStatus(userId, Status.COMPLETED);
        Integer allCount = pendingCount + approvedCount + completedCount;
        return TravelDestinationStatusCountRequestDTO.builder()
                .pendingCount(pendingCount)
                .approvedCount(approvedCount)
                .completedCount(completedCount)
                .allCount(allCount)
                .build();
    }

    @Override
    public void deleteById(String id) {
        TravelDestination travelDestination = travelDestinationRepository.findById(id).orElseThrow();
        travelDestinationRepository.delete(travelDestination);
    }

    // Helper methods

    private PlacesToVisitByValueResponseDTO mapToPlacesToVisitByValueResponseDTO(TravelDestination travelDestination, String imageName) {
        return PlacesToVisitByValueResponseDTO.builder()
                .id(travelDestination.getId())
                .createdByName(travelDestination.getCreatedByName())
                .estimatedCost(travelDestination.getEstimatedCost())
                .imageUrl(imageName)
                .likeCount(travelDestination.getLikeCount())
                .destination(travelDestination.getDestinationName())
                .build();
    }

    private UserCreatedListResponseDTO mapToUserCreatedListResponseDTO(TravelDestination travelDestination) {
        String imageName = getImageNameForTravelDestination(travelDestination);
        List<String> iconList = cardIconListService.findNameByTravelDestinationId(travelDestination.getId());
        return UserCreatedListResponseDTO.builder()
                .id(travelDestination.getId())
                .description(travelDestination.getCategory().getDescription())
                .status(travelDestination.getStatus())
                .destinationName(travelDestination.getDestinationName())
                .estimatedCost(travelDestination.getEstimatedCost())
                .imageUrl(imageName)
                .iconList(iconList)
                .build();
    }

    private String getImageNameForTravelDestination(TravelDestination travelDestination) {
        ImageData imageData = storageRepository.findByTravelDestinationId(travelDestination)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ImageData not found for TravelDestination"));
        return imageData.getName();
    }

    private Integer getCurrentUserId() {
        return userService.getCurrentUser().getId();
    }

    private void validateCustomCardRequest(UserCustomCardRequestDTO customCardRequestDTO) {
        List<String> missingFields = new ArrayList<>();
        if (StringUtils.isBlank(customCardRequestDTO.getDestinationName())) {
            missingFields.add("Destination Name");
        }
        if (customCardRequestDTO.getEstimatedCost() == null) {
            missingFields.add("Estimated Cost");
        }
        if (StringUtils.isBlank(customCardRequestDTO.getUserComments())) {
            missingFields.add("User Comments");
        }
        if (StringUtils.isBlank(customCardRequestDTO.getEvents())) {
            missingFields.add("Events");
        }
        if (StringUtils.isBlank(customCardRequestDTO.getCategoryId())) {
            missingFields.add("Category ID");
        }
        if (!missingFields.isEmpty()) {
            throw new MandatoryException(missingFields);
        }
    }

    private TravelDestination createTravelDestination(UserCustomCardRequestDTO customCardRequestDTO, User user, TravelPlaceCategory placeCategory) {
        TravelDestination travelDestination = TravelDestination.builder()
                .category(placeCategory)
                .estimatedCost(customCardRequestDTO.getEstimatedCost())
                .createdByUser(true)
                .likeCount(0L)
                .destinationName(customCardRequestDTO.getDestinationName())
                .createdBy(user.getId())
                .createdByName(user.getFirstname() + " " + user.getLastname())
                .status(Status.PENDING)
                .build();
        return travelDestinationRepository.save(travelDestination);
    }

    private void createTravelDestinationDetails(UserCustomCardRequestDTO customCardRequestDTO, TravelDestination travelDestination) {
        TravelDestinationDetails travelDestinationDetails = TravelDestinationDetails.builder()
                .travelDestinationDescription(customCardRequestDTO.getUserComments())
                .events(customCardRequestDTO.getEvents())
                .travelDestination(travelDestination)
                .build();
        travelDestinationDetailsService.save(travelDestinationDetails);
    }

    private void createTravelDestinationCardIconList(UserCustomCardRequestDTO customCardRequestDTO, TravelDestination travelDestination) {
        for (String iconName : customCardRequestDTO.getSelectedIcons()) {
            TravelDestinationCardIconList travelDestinationCardIconList = TravelDestinationCardIconList.builder()
                    .iconName(iconName)
                    .travelDestination(travelDestination)
                    .build();
            cardIconListRepository.save(travelDestinationCardIconList);
        }

    }

    private Integer getCountByStatus(Integer userId, Status status) {
        return travelDestinationRepository.createdByUserListStatusCount(userId, status);
    }
}

