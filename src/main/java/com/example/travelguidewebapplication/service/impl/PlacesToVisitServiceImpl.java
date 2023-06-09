package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.PlacesToVisitStatusCountRequestDTO;
import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.PlacesToVisitDetails;
import com.example.travelguidewebapplication.model.TravelPlaceKey;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.PlacesToVisitRepository;
import com.example.travelguidewebapplication.repository.TravelPlaceKeyRepository;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitDetailsService;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlacesToVisitServiceImpl implements PlacesToVisitService {

    private final PlacesToVisitRepository placesToVisitRepository;
    private final TravelPlaceKeyRepository placeKeyRepository;
    private final UserService userService;
    private final PlacesToVisitDetailsService placesToVisitDetailsService;


    @Override
    public List<PlacesToVisit> getByValue(String key) {
//        log.info(key);
        return placesToVisitRepository.findByTravelPlaceKeyValue(key, Status.ICRA_EDILIB);
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
                .manyForTravel(customCardRequestDTO.getManyForTravel())
                .isCreatedByUser(true)
                .imageUrl(customCardRequestDTO.getImageUrl())
                .likeCount(0L)
                .places(customCardRequestDTO.getPlaces())
                .createdBy(user.getId())
                .createdByName(user.getFirstname() + " " + user.getLastname())
                .status(Status.GOZLEMEDE)
                .build();
        placesToVisitRepository.save(places);

        PlacesToVisitDetails placesToVisitDetails = PlacesToVisitDetails.builder()
                .userComments(customCardRequestDTO.getUserComments())
                .events(customCardRequestDTO.getEvents())
                .places(places)
                .build();
        placesToVisitDetailsService.save(placesToVisitDetails);
    }

    @Override
    public PlacesToVisitStatusCountRequestDTO statusCount() {
        User user = userService.getUserByUserName();
        Integer gozlemede = placesToVisitRepository.createdByUserListStatusCount(user.getId(), Status.GOZLEMEDE);
        Integer legvEdilib = placesToVisitRepository.createdByUserListStatusCount(user.getId(), Status.LEGV_EDILIB);
        Integer icraEdilib = placesToVisitRepository.createdByUserListStatusCount(user.getId(), Status.ICRA_EDILIB);
        Integer allCount = gozlemede + legvEdilib + icraEdilib;

        return PlacesToVisitStatusCountRequestDTO.builder()
                .gozlemedeCount(gozlemede)
                .legvEdilibCount(legvEdilib)
                .icraEdilibCount(icraEdilib)
                .allCount(allCount)
                .build();
    }
}
