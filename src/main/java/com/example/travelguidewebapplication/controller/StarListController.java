package com.example.travelguidewebapplication.controller;

import com.example.travelguidewebapplication.DTO.StarRequestDTO;
import com.example.travelguidewebapplication.model.PlacesToVisit;
import com.example.travelguidewebapplication.model.SessionManager;
import com.example.travelguidewebapplication.model.StarList;
import com.example.travelguidewebapplication.service.inter.StartListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/star")
@RequiredArgsConstructor
public class StarListController {

    private final StartListService service;

    @PostMapping("/favorites/add")
    public ResponseEntity<String> addFavorite(@RequestBody StarRequestDTO starRequestDTO) {
        service.add(starRequestDTO);
        return ResponseEntity.ok("Favori eklendi");
    }

    @GetMapping("favorites/check/{rowId}")
    public ResponseEntity<Boolean> isFavorite(@PathVariable String rowId) {
        return ResponseEntity.ok(service.isFavorite(rowId));
    }

    @DeleteMapping("/favorites/delete")
    public ResponseEntity<String> deleteFavorite(@RequestBody StarRequestDTO starRequestDTO) {
        try {
            service.delete(starRequestDTO);
            return ResponseEntity.ok("Favori silindi");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Favori silinirken bir hata oluştu: " + ex.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<SessionManager> getProfile() {
        return ResponseEntity.ok(service.profilDeyisdirmelidir());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlacesToVisit>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
