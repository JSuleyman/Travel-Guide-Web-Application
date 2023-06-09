package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.StarRequestDTO;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.SessionManager;
import com.example.travelguidewebapplication.model.StarList;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.PlacesToVisitRepository;
import com.example.travelguidewebapplication.repository.StartListRepository;
import com.example.travelguidewebapplication.repository.UserRespository;
import com.example.travelguidewebapplication.service.inter.StartListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StartListServiceImpl implements StartListService {

    private final StartListRepository startListRepository;
    private final PlacesToVisitRepository placesToVisitRepository;
    private final UserRespository userRespository;
    private final SessionManager sessionManager;

    @Override
    public void add(StarRequestDTO starRequestDTO) {
        User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow();

        PlacesToVisit places = placesToVisitRepository.findById(starRequestDTO.getId()).orElseThrow(() -> new NoSuchElementException("Tapilmadi"));
        StarList starList = new StarList();
        starList.setPlacesId(places);
        starList.setFavorite(true);
        starList.setUserId(user);
        startListRepository.save(starList);
    }

    @Override
    public boolean isFavorite(String id) {
        try {
            User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow();
            Integer userId = user.getId();
            StarList starList = startListRepository.findByPlacesId_IdAndUserId_Id(id, userId);
            return starList.isFavorite();
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public void delete(StarRequestDTO starRequestDTO) {
        User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow(() -> new NoSuchElementException("User not found"));

        PlacesToVisit places = placesToVisitRepository.findById(starRequestDTO.getId()).orElseThrow(() -> new NoSuchElementException("Place not found"));

        StarList starList = startListRepository.findByUserIdAndPlacesId(user, places);
        if (starList == null) {
            throw new NoSuchElementException("StarList not found");
        }
        startListRepository.delete(starList);
    }

    @Override
    public SessionManager profilDeyisdirmelidir() {
        User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow();
        sessionManager.setFirstName(user.getFirstname());
        sessionManager.setLastName(user.getLastname());

        return sessionManager;
    }

    @Override
    public List<PlacesToVisit> getAll() {
        User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow();
        String id = String.valueOf(user.getId());
        return startListRepository.findStarForUser(id);
    }
}
