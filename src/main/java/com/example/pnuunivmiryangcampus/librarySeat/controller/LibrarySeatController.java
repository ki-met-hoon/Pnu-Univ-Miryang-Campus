package com.example.pnuunivmiryangcampus.librarySeat.controller;

import com.example.pnuunivmiryangcampus.librarySeat.dto.response.LibrarySeatResponse;
import com.example.pnuunivmiryangcampus.librarySeat.service.LibrarySeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/library")
@RestController
public class LibrarySeatController implements LibrarySeatControllerDocs{

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
