package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.TravelPlaceKey;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.PlacesToVisitRepository;
import com.example.travelguidewebapplication.repository.TravelPlaceKeyRepository;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacesToVisitServiceImpl implements PlacesToVisitService {

    private final PlacesToVisitRepository placesToVisitRepository;
    private final TravelPlaceKeyRepository placeKeyRepository;
    private final UserService userService;


    @Override
    public List<PlacesToVisit> getByValue(String key) {
        return placesToVisitRepository.findByTravelPlaceKeyValue(key, Status.GOZLEMEDE);
    }

    @Override
    public List<PlacesToVisit> createdByUserList(String status) {
        User user = userService.getUserByUserName();
        Integer userId = user.getId();

        Status statusEnum = Status.fromValue(status);
        return placesToVisitRepository.createdByUserList(userId, statusEnum);
    }

    @Override
    public void userCustomCard(UserCustomCardRequestDTO customCardRequestDTO) {
        User user = userService.getUserByUserName();
        TravelPlaceKey placeKey = placeKeyRepository.findTravelPlaceKeyByKey(customCardRequestDTO.getKeyId());
        PlacesToVisit places = PlacesToVisit.builder()
                .keyId(placeKey)
                .userComments(customCardRequestDTO.getUserComments())
                .manyForTravel(customCardRequestDTO.getManyForTravel())
                .events(customCardRequestDTO.getEvents())
                .isCreatedByUser(true)
                .imageUrl(customCardRequestDTO.getImageUrl())
                .likeCount(0L)
                .places(customCardRequestDTO.getPlaces())
                .createdBy(user.getId())
                .createdByName(user.getFirstname() + " " + user.getLastname())
                .status(Status.GOZLEMEDE)
                .build();
        placesToVisitRepository.save(places);
    }
}
