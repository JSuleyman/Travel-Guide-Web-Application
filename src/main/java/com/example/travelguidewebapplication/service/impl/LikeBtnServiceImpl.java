package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.LikeBtnDTO;
import com.example.travelguidewebapplication.model.LikeBtn;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.SessionManager;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.LikeBtnRepository;
import com.example.travelguidewebapplication.repository.PlacesToVisitRepository;
import com.example.travelguidewebapplication.repository.UserRespository;
import com.example.travelguidewebapplication.service.inter.LikeBtnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LikeBtnServiceImpl implements LikeBtnService {
    private final LikeBtnRepository likeBtnRepository;
    private final PlacesToVisitRepository placesToVisitRepository;
    private final UserRespository userRespository;
    private final SessionManager sessionManager;

    @Override
    public Long add(LikeBtnDTO likeBtnDTO) {
        PlacesToVisit places = placesToVisitRepository.findById(likeBtnDTO.getId()).orElseThrow(() -> new NoSuchElementException("Tapilmadi"));
        if (isLike(likeBtnDTO.getId())) {

        } else {
            User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow();

            Long count = places.getLikeCount() + 1;
            places.setLikeCount(count);
            placesToVisitRepository.save(places);

            LikeBtn likeBtn = new LikeBtn();
            likeBtn.setPlacesId(places);
            likeBtn.setUserId(user);
            likeBtn.setLike(true);
            likeBtnRepository.save(likeBtn);
            return places.getLikeCount();
        }
        return places.getLikeCount();
    }

    @Override
    public boolean isLike(Long id) {
        try {
            User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow();
            Integer userId = user.getId();
            LikeBtn likeBtn = likeBtnRepository.findByPlacesId_IdAndUserId_Id(id, userId);
            return likeBtn.isLike();
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public Long delete(LikeBtnDTO likeBtnDTO) {
        PlacesToVisit places = placesToVisitRepository.findById(likeBtnDTO.getId()).orElseThrow(() -> new NoSuchElementException("Place not found"));

        if (isLike(likeBtnDTO.getId())) {
            User user = userRespository.findByEmail(sessionManager.getUserName()).orElseThrow(() -> new NoSuchElementException("User not found"));
            Long count = places.getLikeCount() - 1;
            places.setLikeCount(count);
            placesToVisitRepository.save(places);

            LikeBtn likeBtn = likeBtnRepository.findByUserIdAndPlacesId(user, places);
            if (likeBtn == null) {
                throw new NoSuchElementException("StarList not found");
            }
            likeBtnRepository.delete(likeBtn);
            return places.getLikeCount();
        }
        return places.getLikeCount();
    }

    @Override
    public List<PlacesToVisit> getAll() {
        return null;
    }
}
