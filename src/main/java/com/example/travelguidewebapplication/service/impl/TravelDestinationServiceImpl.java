package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.TravelDestinationStatusCountRequestDTO;
import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserCreatedListResponseDTO;
import com.example.travelguidewebapplication.DTO.response.PlacesToVisitByValueResponseDTO;
import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.exception.MandatoryException;
import com.example.travelguidewebapplication.model.*;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.repository.StorageRepository;
import com.example.travelguidewebapplication.repository.TravelPlaceCategoryRepository;
import com.example.travelguidewebapplication.service.inter.TravelDestinationDetailsService;
import com.example.travelguidewebapplication.service.inter.TravelDestinationService;
import com.example.travelguidewebapplication.service.inter.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelDestinationServiceImpl implements TravelDestinationService {

    private final TravelDestinationRepository travelDestinationRepository;
    private final TravelPlaceCategoryRepository placeKeyRepository;
    private final UserService userService;
    private final TravelDestinationDetailsService travelDestinationDetailsService;
    private final StorageRepository storageRepository;

    @Override
    @Transactional
    public List<PlacesToVisitByValueResponseDTO> getByValue(String key) {
        List<PlacesToVisitByValueResponseDTO> placesToVisitByValueResponseDTO = new ArrayList<>();
        List<TravelDestination> places = travelDestinationRepository.findByTravelPlaceKeyValue(key, Status.COMPLETED);

        for (TravelDestination places1 : places) {
            ImageData imageData = storageRepository.findByTravelDestinationId(places1).get(0);
            String imgName = imageData.getName();

            PlacesToVisitByValueResponseDTO placesToVisitByValueResponseDTO1 = PlacesToVisitByValueResponseDTO.builder()
                    .id(places1.getId())
                    .createdByName(places1.getCreatedByName())
                    .estimatedCost(places1.getEstimatedCost())
                    .imageUrl(imgName)
                    .likeCount(places1.getLikeCount())
                    .destination(places1.getDestinationName())
                    .build();
            placesToVisitByValueResponseDTO.add(placesToVisitByValueResponseDTO1);
        }
        return placesToVisitByValueResponseDTO;
    }

    @Override
    @Transactional
    public List<UserCreatedListResponseDTO> createdByUserList(String status) {
        List<UserCreatedListResponseDTO> userCreatedListResponseDTOS = new ArrayList<>();
        User user = userService.getUserByUserName();
        Integer userId = user.getId();

        Status statusEnum = Status.fromValue(status);

        List<TravelDestination> places = travelDestinationRepository.createdByUserList(userId, statusEnum);
        for (TravelDestination places1 : places) {
            ImageData imageData = storageRepository.findByTravelDestinationId(places1).get(0);
            String imgName = imageData.getName();

            UserCreatedListResponseDTO userCreatedListResponseDTO = UserCreatedListResponseDTO.builder()
                    .id(places1.getId())
                    .description(places1.getCategory().getDescription())
                    .status(places1.getStatus())
                    .destinationName(places1.getDestinationName())
                    .estimatedCost(places1.getEstimatedCost())
                    .imageUrl(imgName)
                    .build();
            userCreatedListResponseDTOS.add(userCreatedListResponseDTO);
        }
        return userCreatedListResponseDTOS;
    }

    @Override
    public String userCustomCard(UserCustomCardRequestDTO customCardRequestDTO) {
        List<String> missingFields = new ArrayList<>();

        if (customCardRequestDTO.getDestinationName() == null || customCardRequestDTO.getDestinationName().trim().equals("")) {
            missingFields.add("Places");
        }

        if (customCardRequestDTO.getEstimatedCost() == null) {
            missingFields.add("Money");
        }

        if (customCardRequestDTO.getUserComments() == null || customCardRequestDTO.getUserComments().trim().equals("")) {
            missingFields.add("Comment");
        }

        if (customCardRequestDTO.getEvents() == null || customCardRequestDTO.getEvents().trim().equals("")) {
            missingFields.add("Events");
        }

        if (customCardRequestDTO.getCategoryId() == null || customCardRequestDTO.getCategoryId().trim().equals("")) {
            missingFields.add("Key Id");
        }

        if (!missingFields.isEmpty()) {
            throw new MandatoryException(missingFields);
        }
        User user = userService.getUserByUserName();
        TravelPlaceCategory placeCategory = placeKeyRepository.findTravelPlaceKeyByKey(customCardRequestDTO.getCategoryId());


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
        TravelDestination travelDestination1 = travelDestinationRepository.save(travelDestination);

        TravelDestinationDetails travelDestinationDetails = TravelDestinationDetails.builder()
                .travelDestinationDescription(customCardRequestDTO.getUserComments())
                .events(customCardRequestDTO.getEvents())
                .travelDestination(travelDestination)
                .build();
        travelDestinationDetailsService.save(travelDestinationDetails);
        return travelDestination1.getId();
    }

    @Override
    public TravelDestinationStatusCountRequestDTO statusCount() {
        User user = userService.getUserByUserName();

        Integer gozlemede = countByStatus(user.getId(), Status.PENDING);
        Integer legvEdilib = countByStatus(user.getId(), Status.APPROVED);
        Integer icraEdilib = countByStatus(user.getId(), Status.COMPLETED);
        Integer allCount = gozlemede + legvEdilib + icraEdilib;

        return TravelDestinationStatusCountRequestDTO.builder()
                .pendingCount(gozlemede)
                .approvedCount(legvEdilib)
                .completedCount(icraEdilib)
                .allCount(allCount)
                .build();
    }

    @Override
    public void deleteById(String id) {
        TravelDestination travelDestination = travelDestinationRepository.findById(id).orElseThrow();
        travelDestinationRepository.delete(travelDestination);
    }

    private Integer countByStatus(Integer userId, Status status) {
        return travelDestinationRepository.createdByUserListStatusCount(userId, status);
    }
}
