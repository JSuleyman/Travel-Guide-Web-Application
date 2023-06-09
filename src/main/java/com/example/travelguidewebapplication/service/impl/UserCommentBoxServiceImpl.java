package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserCommentBoxDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentBoxResponseDTO;
import com.example.travelguidewebapplication.exception.EmptyMessageException;
import com.example.travelguidewebapplication.model.PlacesToVisitDetails;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.UserCommentBox;
import com.example.travelguidewebapplication.repository.UserCommentBoxRepository;
import com.example.travelguidewebapplication.service.inter.PlacesToVisitDetailsService;
import com.example.travelguidewebapplication.service.inter.UserCommentBoxService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommentBoxServiceImpl implements UserCommentBoxService {
    private final UserCommentBoxRepository userCommentBoxRepository;
    private final UserService userService;
    private final PlacesToVisitDetailsService placesToVisitDetailsService;

    @Override
    public void save(UserCommentBoxDTO userCommentBoxDTO) {
        if (userCommentBoxDTO.getUserMessage().trim().length() == 0) {
            throw new EmptyMessageException();
        } else {
            User user = userService.getUserByUserName();
            PlacesToVisitDetails places = placesToVisitDetailsService.getById(userCommentBoxDTO.getFkDetailsId());

            UserCommentBox userCommentBox = UserCommentBox.builder()
                    .fkUserId(user)
                    .fkPlacesToVisitDetailsId(places)
                    .messagesList(userCommentBoxDTO.getUserMessage())
                    .localDateTime(LocalDateTime.now())
                    .build();
            userCommentBoxRepository.save(userCommentBox);
        }
    }

    @Override
    public List<UserCommentBoxResponseDTO> getUserCommentListByPlacesId(Long id) {
        List<UserCommentBox> userCommentBox = userCommentBoxRepository.findByFkPlacesToVisitDetailsIdPlacesId(id);

        List<UserCommentBoxResponseDTO> userCommentBoxResponseDTO = new ArrayList<>();

        for (UserCommentBox userCommentBox1 : userCommentBox) {
            UserCommentBoxResponseDTO userCommentBoxResponseDTOIn = UserCommentBoxResponseDTO.builder()
                    .userMessage(userCommentBox1.getMessagesList())
                    .firstName(userCommentBox1.getFkUserId().getFirstname())
                    .lastName(userCommentBox1.getFkUserId().getLastname())
                    .dateAndTime(userCommentBox1.getLocalDateTime())
                    .build();
            userCommentBoxResponseDTO.add(userCommentBoxResponseDTOIn);
        }

        return userCommentBoxResponseDTO;
    }
}
