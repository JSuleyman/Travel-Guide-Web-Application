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
import com.example.travelguidewebapplication.util.DateHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCommentServiceImpl implements UserCommentService {
    private final UserCommentRepository userCommentRepository;
    private final UserService userService;
    private final TravelDestinationDetailsService travelDestinationDetailsService;
    private final NotificationService notificationService;
    private final TravelDestinationRepository travelDestinationRepository;

//    @Override
//    public void save(UserCommentDTO userCommentDTO) {
//        if (userCommentDTO.getUserComment().trim().length() == 0) {
//            throw new EmptyMessageException();
//        } else {
//            User user = userService.getCurrentUser();
//            TravelDestinationDetails travelDestinationDetails = travelDestinationDetailsService.getById(userCommentDTO.getTravelDestinationDetailsId());
//            if (travelDestinationDetails.getTravelDestination().getStatus().equals(Status.COMPLETED)) {
//                UserComment userComment = UserComment.builder()
//                        .userId(user)
//                        .travelDestinationDetailsId(travelDestinationDetails)
//                        .commentList(userCommentDTO.getUserComment())
//                        .localDateTime(DateHelper.getAzerbaijanDateTime())
//                        .build();
//                userCommentRepository.save(userComment);
//
//                if (!Objects.equals(user.getId(), travelDestinationRepository.findById(travelDestinationDetails.getTravelDestination().getId()).get().getCreatedBy())) {
//                    notificationService.save(
//                            Notification.builder()
//                                    .fkUserId(travelDestinationRepository.findById(travelDestinationDetails.getTravelDestination().getId()).get().getCreatedBy())
//                                    .fkUserCommentId(userComment.getId())
//                                    .fkTravelDestinationId(travelDestinationDetails.getTravelDestination().getId())
//                                    .isNewComment(true)
//                                    .build());
//                }
//            }
//        }
//    }

    @Override
    public void save(UserCommentDTO userCommentDTO) {
        String trimmedComment = userCommentDTO.getUserComment().trim();
        validateCommentNotEmpty(trimmedComment);

        User user = userService.getCurrentUser();
        TravelDestinationDetails travelDestinationDetails = travelDestinationDetailsService.getById(userCommentDTO.getTravelDestinationDetailsId());

        if (isTravelDestinationCompleted(travelDestinationDetails)) {
            UserComment userComment = createUserComment(user, travelDestinationDetails, trimmedComment);

            Integer createdByUserId = travelDestinationDetails.getTravelDestination().getCreatedBy();
            if (!user.getId().equals(createdByUserId)) {
                createNewCommentNotification(createdByUserId, userComment, travelDestinationDetails.getTravelDestination().getId());
            }
        }
    }

    @Override
    public List<UserCommentBoxResponseDTO> getUserCommentListByPlacesId(String id, int first, int offset) {
        Pageable pageable = PageRequest.of(first, offset);
        List<UserComment> userComments = userCommentRepository.findByFkPlacesToVisitDetailsIdPlacesId(id, Status.COMPLETED, pageable);
        return userComments.stream()
                .map(this::mapToUserCommentBoxResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Integer currentUserId() {
        return userService.getCurrentUser().getId();
    }

    //Helper Methods

    private void validateCommentNotEmpty(String comment) {
        if (comment.isEmpty()) {
            throw new EmptyMessageException();
        }
    }

    private boolean isTravelDestinationCompleted(TravelDestinationDetails travelDestinationDetails) {
        return travelDestinationDetails.getTravelDestination().getStatus() == Status.COMPLETED;
    }

    private UserComment createUserComment(User user, TravelDestinationDetails travelDestinationDetails, String comment) {
        return userCommentRepository.save(
                UserComment.builder()
                        .userId(user)
                        .travelDestinationDetailsId(travelDestinationDetails)
                        .commentList(comment)
                        .commentReplyCount(0L)
                        .localDateTime(DateHelper.getAzerbaijanDateTime())
                        .build()
        );
    }

    private void createNewCommentNotification(Integer userId, UserComment userComment, String travelDestinationId) {
        notificationService.save(
                Notification.builder()
                        .fkUserId(userId)
                        .fkUserCommentId(userComment.getId())
                        .fkTravelDestinationId(travelDestinationId)
                        .isNewComment(true)
                        .build()
        );
    }

    private UserCommentBoxResponseDTO mapToUserCommentBoxResponseDTO(UserComment userComment) {
        return UserCommentBoxResponseDTO.builder()
                .userMessage(userComment.getCommentList())
                .commentReplyCount(userComment.getCommentReplyCount())
                .firstName(userComment.getUserId().getFirstname())
                .lastName(userComment.getUserId().getLastname())
                .dateAndTime(userComment.getLocalDateTime())
                .userId(userComment.getUserId().getId())
                .currentUserId(userService.getCurrentUser().getId())
                .id(userComment.getId())
                .build();
    }
}
