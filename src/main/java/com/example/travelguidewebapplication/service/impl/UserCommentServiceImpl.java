package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.UserCommentDTO;
import com.example.travelguidewebapplication.DTO.response.UserCommentBoxResponseDTO;
import com.example.travelguidewebapplication.enums.Status;
import com.example.travelguidewebapplication.exception.EmptyMessageException;
import com.example.travelguidewebapplication.model.Notification;
import com.example.travelguidewebapplication.model.TravelDestinationDetails;
import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.model.UserComment;
import com.example.travelguidewebapplication.repository.TravelDestinationRepository;
import com.example.travelguidewebapplication.repository.UserCommentRepository;
import com.example.travelguidewebapplication.service.inter.NotificationService;
import com.example.travelguidewebapplication.service.inter.TravelDestinationDetailsService;
import com.example.travelguidewebapplication.service.inter.UserCommentService;
import com.example.travelguidewebapplication.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCommentServiceImpl implements UserCommentService {
    private final UserCommentRepository userCommentRepository;
    private final UserService userService;
    private final TravelDestinationDetailsService travelDestinationDetailsService;
    private final NotificationService notificationService;
    private final TravelDestinationRepository travelDestinationRepository;

    @Override
    public void save(UserCommentDTO userCommentDTO) {
        if (userCommentDTO.getUserComment().trim().length() == 0) {
            throw new EmptyMessageException();
        } else {
            Calendar azerbaijanCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Baku"));
            int year = azerbaijanCalendar.get(Calendar.YEAR);
            int month = azerbaijanCalendar.get(Calendar.MONTH) + 1;  // Ay, 0-indeksli olarak döndürüldüğü için +1 ekliyoruz
            int day = azerbaijanCalendar.get(Calendar.DAY_OF_MONTH);
            int hour = azerbaijanCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = azerbaijanCalendar.get(Calendar.MINUTE);
            int second = azerbaijanCalendar.get(Calendar.SECOND);

            User user = userService.getCurrentUser();
            TravelDestinationDetails travelDestinationDetails = travelDestinationDetailsService.getById(userCommentDTO.getTravelDestinationDetailsId());
            if (travelDestinationDetails.getTravelDestination().getStatus().equals(Status.COMPLETED)) {
                UserComment userComment = UserComment.builder()
                        .userId(user)
                        .travelDestinationDetailsId(travelDestinationDetails)
                        .commentList(userCommentDTO.getUserComment())
                        .localDateTime(LocalDateTime.of(year, month, day, hour, minute, second))
                        .build();
                userCommentRepository.save(userComment);

                if (!Objects.equals(user.getId(), travelDestinationRepository.findById(travelDestinationDetails.getTravelDestination().getId()).get().getCreatedBy())) {
                    notificationService.save(
                            Notification.builder()
                                    .fkUserId(travelDestinationRepository.findById(travelDestinationDetails.getTravelDestination().getId()).get().getCreatedBy())
                                    .fkUserCommentId(userComment.getId())
                                    .fkTravelDestinationId(travelDestinationDetails.getTravelDestination().getId())
                                    .isNewComment(true)
                                    .build());
                }
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
        return userService.getCurrentUser().getId();
    }
}
