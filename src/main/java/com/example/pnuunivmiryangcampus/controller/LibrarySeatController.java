package com.example.pnuunivmiryangcampus.controller;

import com.example.pnuunivmiryangcampus.dto.response.LibrarySeatResponse;
import com.example.pnuunivmiryangcampus.service.LibrarySeatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/library")
@RestController
public class LibrarySeatController {

    private final LibrarySeatService librarySeatService;

    @GetMapping("/seats")
    public ResponseEntity<List<LibrarySeatResponse>> librarySeats() {

        List<LibrarySeatResponse> availableSeats = librarySeatService.getAvailableLibrarySeat()
                .stream()
                .map(LibrarySeatResponse::from)
                .toList();

        return ResponseEntity.ok(availableSeats);
    }
}
