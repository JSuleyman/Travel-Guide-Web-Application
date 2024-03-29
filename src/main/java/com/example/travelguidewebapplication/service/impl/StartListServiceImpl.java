package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.StarRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserStarListResponseDTO;
import com.example.travelguidewebapplication.model.*;
import com.example.travelguidewebapplication.repository.StartListRepository;
import com.example.travelguidewebapplication.repository.StorageRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.repository.UserRepository;
import com.example.travelguidewebapplication.service.inter.StartListService;
import com.example.travelguidewebapplication.service.inter.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StartListServiceImpl implements StartListService {

    private final StartListRepository startListRepository;
    private final TravelDestinationRepository travelDestinationRepository;
    private final UserRepository userRepository;
    private final SessionManager sessionManager;
    private final StorageRepository storageRepository;
    private final UserService userService;

    @Override
    public void add(StarRequestDTO starRequestDTO) {
        User user = userRepository.findByEmail(sessionManager.getUserName()).orElseThrow();

        TravelDestination travelDestination = travelDestinationRepository.findById(starRequestDTO.getId()).orElseThrow(() -> new NoSuchElementException("Tapilmadi"));
        StarList starList = new StarList();
        starList.setTravelDestination(travelDestination);
        starList.setFavorite(true);
        starList.setUserId(user);
        startListRepository.save(starList);
    }

    @Override
    public boolean isFavorite(String id) {
        try {
            User user = userRepository.findByEmail(sessionManager.getUserName()).orElseThrow();
            Integer userId = user.getId();
            StarList starList = startListRepository.findByTravelDestination_IdAndUserId_Id(id, userId);
            return starList.isFavorite();
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public void delete(StarRequestDTO starRequestDTO) {
        User user = userRepository.findByEmail(sessionManager.getUserName()).orElseThrow(() -> new NoSuchElementException("User not found"));

        TravelDestination travelDestination = travelDestinationRepository.findById(starRequestDTO.getId()).orElseThrow(() -> new NoSuchElementException("Place not found"));

        StarList starList = startListRepository.findByUserIdAndTravelDestination(user, travelDestination);
        if (starList == null) {
            throw new NoSuchElementException("StarList not found");
        }
        startListRepository.delete(starList);
    }

    @Override
    public SessionManager profilDeyisdirmelidir() {
        User user = userRepository.findByEmail(sessionManager.getUserName()).orElseThrow();
        sessionManager.setFirstName(user.getFirstname());
        sessionManager.setLastName(user.getLastname());

        return sessionManager;
    }

    @Transactional
    @Override
    public List<UserStarListResponseDTO> getAll() {
        List<TravelDestination> travelDestinationList = startListRepository.findStarForUser(userService.getCurrentUser().getId());

        return travelDestinationList.stream()
                .map(this::mapToUserStarListResponseDTO)
                .collect(Collectors.toList());
    }

    private UserStarListResponseDTO mapToUserStarListResponseDTO(TravelDestination travelDestination) {
        String imageName = getImageNameForTravelDestination(travelDestination);
        return UserStarListResponseDTO.builder()
                .id(travelDestination.getId())
                .destinationName(travelDestination.getDestinationName())
                .estimatedCost(travelDestination.getEstimatedCost())
                .imageUrl(imageName)
                .build();
    }

    private String getImageNameForTravelDestination(TravelDestination travelDestination) {
        ImageData imageData = storageRepository.findByTravelDestinationId(travelDestination)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ImageData not found for TravelDestination"));
        return imageData.getName();
    }
}
