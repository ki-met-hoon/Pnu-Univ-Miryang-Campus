package com.example.pnuunivmiryangcampus.controller;

import com.example.pnuunivmiryangcampus.dto.ReservationDto;
import com.example.pnuunivmiryangcampus.service.ReservationService;
import java.net.URI;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/library/reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{seatId}")
    public ResponseEntity<Void> reservation(@PathVariable Long seatId) {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(4);

        //kakao 로그인 서비스 구현 후 1L을 security에서 받아온 ID 값으로 변경해야함
        ReservationDto reservationDto = ReservationDto.of(1L, seatId, startAt, endAt);
        Long reservationId = reservationService.saveReservationAndReturnId(reservationDto);

        return ResponseEntity.created(URI.create("/library/reservation/" + reservationId)).build();
    }
}
