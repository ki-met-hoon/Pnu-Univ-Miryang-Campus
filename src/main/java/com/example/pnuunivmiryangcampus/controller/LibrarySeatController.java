package com.example.pnuunivmiryangcampus.controller;

import com.example.pnuunivmiryangcampus.dto.response.LibrarySeatResponse;
import com.example.pnuunivmiryangcampus.service.LibrarySeatService;
import com.example.pnuunivmiryangcampus.util.ApiUtils;
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
    public ResponseEntity<?> librarySeats() {

        List<LibrarySeatResponse> unavailableSeats = librarySeatService.getUnavailableLibrarySeat()
                .stream()
                .map(LibrarySeatResponse::from)
                .toList();

        return ResponseEntity.ok().body(ApiUtils.success(unavailableSeats));
    }
}
