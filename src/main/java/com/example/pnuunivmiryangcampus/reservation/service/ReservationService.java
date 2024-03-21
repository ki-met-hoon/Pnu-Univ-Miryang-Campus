package com.example.pnuunivmiryangcampus.reservation.service;

import com.example.pnuunivmiryangcampus.reservation.dto.ReservationDto;
import com.example.pnuunivmiryangcampus.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public void saveReservation(ReservationDto dto) {
        reservationRepository.save(dto.toEntity());
    }
}
