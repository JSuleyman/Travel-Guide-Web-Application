package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.TravelDestinationStatusCountRequestDTO;
import com.example.travelguidewebapplication.DTO.UserCustomCardRequestDTO;
import com.example.travelguidewebapplication.DTO.response.UserCreatedListResponseDTO;
import com.example.travelguidewebapplication.DTO.response.PlacesToVisitByValueResponseDTO;
//import com.example.travelguidewebapplication.logger.CustomLogger;
import com.example.travelguidewebapplication.service.inter.TravelDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel_destination")
@RequiredArgsConstructor
public class TravelDestinationController {
    private final TravelDestinationService travelDestinationService;
//    private final CustomLogger customLogger;

    @GetMapping("/get")
    public ResponseEntity<List<PlacesToVisitByValueResponseDTO>> getByValue(@RequestParam String key) {
        return ResponseEntity.ok(travelDestinationService.getByValue(key));
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody UserCustomCardRequestDTO userCustomCardRequestDTO) throws IllegalAccessException {
        return ResponseEntity.ok(travelDestinationService.createUserCustomCard(userCustomCardRequestDTO));
    }

    @GetMapping("created_by_user_list")
    public ResponseEntity<List<UserCreatedListResponseDTO>> createdByUserList(@RequestParam(value = "status", required = false) String status) {
//        customLogger.logRequest("GET", "/created_by_user_list");
        return ResponseEntity.ok(travelDestinationService.createdByUserList(status));
    }

    @GetMapping("created_by_user_list_count")
    public ResponseEntity<TravelDestinationStatusCountRequestDTO> createdByUserListCount() {
        return ResponseEntity.ok(travelDestinationService.statusCount());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(value = "id") String fkPlacesToVisitId) {
        travelDestinationService.deleteById(fkPlacesToVisitId);
    }
}
