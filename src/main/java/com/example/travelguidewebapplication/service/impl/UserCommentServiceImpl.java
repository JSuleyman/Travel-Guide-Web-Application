package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserCommentDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentBoxResponseDTO;
import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.exception.EmptyMessageException;
import com.example.travelguidewebapplication.model.TravelDestinationDetails;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.UserComment;
import com.example.travelguidewebapplication.repository.UserCommentRepository;
import com.example.travelguidewebapplication.service.inter.TravelDestinationDetailsService;
import com.example.travelguidewebapplication.service.inter.UserCommentService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCommentServiceImpl implements UserCommentService {
    private final UserCommentRepository userCommentRepository;
    private final UserService userService;
    private final TravelDestinationDetailsService travelDestinationDetailsService;

    @Override
    public void save(UserCommentDTO userCommentDTO) {
        if (userCommentDTO.getUserComment().trim().length() == 0) {
            throw new EmptyMessageException();
        } else {

            User user = userService.getUserByUserName();
            TravelDestinationDetails travelDestinationDetails = travelDestinationDetailsService.getById(userCommentDTO.getTravelDestinationDetailsId());
            if (travelDestinationDetails.getTravelDestination().getStatus().equals(Status.COMPLETED)) {
                UserComment userComment = UserComment.builder()
                        .userId(user)
                        .travelDestinationDetailsId(travelDestinationDetails)
                        .commentList(userCommentDTO.getUserComment())
                        .localDateTime(LocalDateTime.now())
                        .build();
                userCommentRepository.save(userComment);
            } else {
                log.info("Write error message!");
            }
        }
    }

    @Override
    public List<UserCommentBoxResponseDTO> getUserCommentListByPlacesId(String id) {
        List<UserComment> userComments = userCommentRepository.findByFkPlacesToVisitDetailsIdPlacesId(id, Status.COMPLETED);

        List<UserCommentBoxResponseDTO> userCommentBoxResponseDTO = new ArrayList<>();

        for (UserComment userComment1 : userComments) {
            UserCommentBoxResponseDTO userCommentBoxResponseDTOIn = UserCommentBoxResponseDTO.builder()
                    .userMessage(userComment1.getCommentList())
                    .firstName(userComment1.getUserId().getFirstname())
                    .lastName(userComment1.getUserId().getLastname())
                    .dateAndTime(userComment1.getLocalDateTime())
                    .userId(userComment1.getUserId().getId())
                    .id(userComment1.getId())
                    .build();
            userCommentBoxResponseDTO.add(userCommentBoxResponseDTOIn);
        }

        return userCommentBoxResponseDTO;
    }

    @Override
    public Integer currentUserId() {
        return userService.getUserByUserName().getId();
    }
}
