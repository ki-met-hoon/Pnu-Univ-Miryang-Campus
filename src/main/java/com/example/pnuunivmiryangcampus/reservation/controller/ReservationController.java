package com.example.pnuunivmiryangcampus.reservation.controller;

import com.example.pnuunivmiryangcampus.config.security.CustomUserDetails;
import com.example.pnuunivmiryangcampus.reservation.dto.ReservationDto;
import com.example.pnuunivmiryangcampus.reservation.dto.response.ReservationResponse;
import com.example.pnuunivmiryangcampus.reservation.service.ReservationService;
import com.example.pnuunivmiryangcampus.userAccount.UserAccount;
import com.example.pnuunivmiryangcampus.userAccount.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/library/reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final UserAccountRepository userAccountRepository;

    @PostMapping("/{seatId}")
    public ResponseEntity<Void> reservation(@PathVariable Long seatId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(4);
        UserAccount findUser = userAccountRepository.findBySub(customUserDetails.getPassword()).orElseThrow();

        ReservationDto reservationDto = ReservationDto.of(findUser.getId(), seatId, startAt, endAt);
        reservationService.saveReservation(reservationDto);

        return ResponseEntity.created(URI.create("/library/reservation/" + findUser.getId())).build();
    }

    @GetMapping
    public ResponseEntity<ReservationResponse> getReservation(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        UserAccount findUser = userAccountRepository.findBySub(customUserDetails.getPassword()).orElseThrow();
        ReservationResponse reservationResponse = reservationService.getReservationByUserId(findUser.getId());

        if (reservationResponse == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reservationService.getReservationByUserId(findUser.getId()));
    }
}
