package com.example.pnuunivmiryangcampus.reservation.service;

import com.example.pnuunivmiryangcampus.librarySeat.repository.LibrarySeatRepository;
import com.example.pnuunivmiryangcampus.reservation.Reservation;
import com.example.pnuunivmiryangcampus.reservation.dto.ReservationDto;
import com.example.pnuunivmiryangcampus.reservation.dto.response.ReservationRenewalResponse;
import com.example.pnuunivmiryangcampus.reservation.dto.response.ReservationResponse;
import com.example.pnuunivmiryangcampus.reservation.exception.ReservationLimitExceededException;
import com.example.pnuunivmiryangcampus.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LibrarySeatRepository librarySeatRepository;

    @Transactional
    public void saveReservation(ReservationDto dto) {
        reservationRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public ReservationResponse getReservationByUserId(Long userId) {
        Optional<Reservation> reservation = reservationRepository.findByUserAccountId(userId);

        if (reservation.isPresent()) {
            Long reservedSeatNumber = librarySeatRepository.findById(reservation.get().getLibrarySeatId()).orElseThrow().getId();

            return ReservationResponse.from(reservation.get(), reservedSeatNumber);
        }

        return null;
    }

    @Transactional
    public ReservationRenewalResponse updateReservationRenewalCount(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();

        reservation.update(reservation.getEndAt().plusHours(3), reservation.getRenewalCount() + 1);

        return ReservationRenewalResponse.from(reservation);
    }
}
