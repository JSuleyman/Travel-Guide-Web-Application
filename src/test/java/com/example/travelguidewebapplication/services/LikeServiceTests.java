package com.example.travelguidewebapplication.services;

import com.example.travelguidewebapplication.model.LikeBtn;
import com.example.travelguidewebapplication.model.TravelDestination;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.LikeBtnRepository;
import com.example.travelguidewebapplication.service.impl.LikeBtnServiceImpl;
import com.example.travelguidewebapplication.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTests {
    @Mock
    private UserService userService;

    @Mock
    private LikeBtnRepository likeBtnRepository;

    @InjectMocks
    private LikeBtnServiceImpl likeBtnService;

    @Test
    public void testIsLike_ShouldReturnTrue_WhenUserLikesDestination() {
        User user = new User();
        user.setId(123);

        LikeBtn likeBtn = new LikeBtn();
        likeBtn.setId("likeBtn123");
        likeBtn.setLike(true);
        likeBtn.setUserId(user);

        TravelDestination travelDestination = new TravelDestination();
        travelDestination.setId("destination123");
        likeBtn.setTravelDestination(travelDestination);

        when(userService.getCurrentUser()).thenReturn(user);
        when(likeBtnRepository.findByTravelDestination_IdAndUserId_Id(travelDestination.getId(), userService.getCurrentUser().getId()))
                .thenReturn(likeBtn);

        assertTrue(likeBtnService.isLike(travelDestination.getId()));
    }

}
