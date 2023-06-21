package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.LikeBtnDTO;
import com.example.travelguidewebapplication.model.LikeBtn;
import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.LikeBtnRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.service.inter.LikeBtnService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LikeBtnServiceImpl implements LikeBtnService {
    private final LikeBtnRepository likeBtnRepository;
    private final TravelDestinationRepository travelDestinationRepository;
    private final UserService userService;

    @Override
    public boolean isLike(String id) {
        User user = userService.getCurrentUser();
        LikeBtn likeBtn = likeBtnRepository.findByTravelDestination_IdAndUserId_Id(id, user.getId());
        return likeBtn != null && likeBtn.isLike();
    }

    @Override
    public Long add(LikeBtnDTO likeBtnDTO) {
        TravelDestination travelDestination = getTravelDestinationById(likeBtnDTO.getId());

        if (isLike(likeBtnDTO.getId())) {
            return travelDestination.getLikeCount(); // Already liked
        }

        User user = userService.getCurrentUser();
        incrementLikeCount(travelDestination);
        LikeBtn likeBtn = createLikeBtn(travelDestination, user, true);
        likeBtnRepository.save(likeBtn);

        return travelDestination.getLikeCount();
    }

    @Override
    public Long delete(LikeBtnDTO likeBtnDTO) {
        TravelDestination travelDestination = getTravelDestinationById(likeBtnDTO.getId());

        if (!isLike(likeBtnDTO.getId())) {
            return travelDestination.getLikeCount(); // Not liked yet
        }

        User user = userService.getCurrentUser();
        decrementLikeCount(travelDestination);
        LikeBtn likeBtn = getLikeBtnByUserAndTravelDestination(user, travelDestination);
        likeBtnRepository.delete(likeBtn);

        return travelDestination.getLikeCount();
    }

    @Override
    public List<TravelDestination> getAll() {
        return travelDestinationRepository.findAll();
    }


    private TravelDestination getTravelDestinationById(String id) {
        return travelDestinationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TravelDestination not found"));
    }

    private void incrementLikeCount(TravelDestination travelDestination) {
        Long currentCount = travelDestination.getLikeCount();
        travelDestination.setLikeCount(currentCount + 1);
        travelDestinationRepository.save(travelDestination);
    }

    private void decrementLikeCount(TravelDestination travelDestination) {
        Long currentCount = travelDestination.getLikeCount();
        travelDestination.setLikeCount(currentCount - 1);
        travelDestinationRepository.save(travelDestination);
    }

    private LikeBtn createLikeBtn(TravelDestination travelDestination, User user, boolean isLike) {
        LikeBtn likeBtn = new LikeBtn();
        likeBtn.setTravelDestination(travelDestination);
        likeBtn.setUserId(user);
        likeBtn.setLike(isLike);
        return likeBtn;
    }

    private LikeBtn getLikeBtnByUserAndTravelDestination(User user, TravelDestination travelDestination) {
        return likeBtnRepository.findByUserIdAndTravelDestination(user, travelDestination);
    }
}
