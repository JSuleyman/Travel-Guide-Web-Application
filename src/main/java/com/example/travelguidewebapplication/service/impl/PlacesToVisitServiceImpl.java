package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.SessionManager;
import com.example.travelguidewebapplication.model.TravelPlaceKey;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.PlacesToVisitRepository;
import com.example.travelguidewebapplication.repository.TravelPlaceKeyRepository;
import com.example.travelguidewebapplication.repository.UserRespository;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacesToVisitServiceImpl implements PlacesToVisitService {

    private final PlacesToVisitRepository placesToVisitRepository;
    private final TravelPlaceKeyRepository placeKeyRepository;
    private final UserRespository userRespository;
    private final SessionManager sessionManager;

    @Override
    public List<PlacesToVisit> getByValue(String key) {
        return placesToVisitRepository.findByTravelPlaceKeyValue(key);
    }

    @Override
    public void userCustomCard(UserCustomCardRequestDTO customCardRequestDTO) {
        User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow();
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
                .build();
        placesToVisitRepository.save(places);
    }
}
