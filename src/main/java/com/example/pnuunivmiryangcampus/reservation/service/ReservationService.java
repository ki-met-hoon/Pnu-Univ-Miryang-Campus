package com.example.pnuunivmiryangcampus.reservation.service;

import com.example.pnuunivmiryangcampus.librarySeat.LibrarySeat;
import com.example.pnuunivmiryangcampus.librarySeat.exception.LibrarySeatNotFoundException;
import com.example.pnuunivmiryangcampus.librarySeat.repository.LibrarySeatRepository;
import com.example.pnuunivmiryangcampus.reservation.Reservation;
import com.example.pnuunivmiryangcampus.reservation.dto.ReservationDto;
import com.example.pnuunivmiryangcampus.reservation.dto.response.ReservationRenewalResponse;
import com.example.pnuunivmiryangcampus.reservation.dto.response.ReservationResponse;
import com.example.pnuunivmiryangcampus.reservation.exception.DuplicateReservationException;
import com.example.pnuunivmiryangcampus.reservation.exception.ExpiredRenewalTimeException;
import com.example.pnuunivmiryangcampus.reservation.exception.ReservationLimitExceededException;
import com.example.pnuunivmiryangcampus.reservation.exception.ReservationNotFoundException;
import com.example.pnuunivmiryangcampus.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LibrarySeatRepository librarySeatRepository;

    @Transactional
    public void saveReservation(ReservationDto dto) {
        checkDuplicateReservation(dto.userAccountId());

        LibrarySeat librarySeat = librarySeatRepository.findById(dto.librarySeatId()).orElseThrow(LibrarySeatNotFoundException::new);
        librarySeat.updateUnavailable();
        reservationRepository.save(dto.toEntity());
    }

    private void checkDuplicateReservation(Long userId) {
        ReservationResponse reservationResponse = this.getReservationByUserId(userId);

        if (reservationResponse != null) {
            throw new DuplicateReservationException();
        }
    }

    @Transactional(readOnly = true)
    public ReservationResponse getReservationByUserId(Long userId) {
        Optional<Reservation> reservation = reservationRepository.findByUserAccountId(userId);

        if (reservation.isPresent()) {
            Long reservedSeatId = librarySeatRepository.findById(reservation.get().getLibrarySeatId()).orElseThrow(LibrarySeatNotFoundException::new).getId();

            return ReservationResponse.from(reservation.get(), reservedSeatId);
        }

        return null;
    }

    @Transactional
    public ReservationRenewalResponse updateReservationRenewalCount(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);

        checkRenewalTime(reservation);

        reservation.update(reservation.getEndAt().plusHours(3), reservation.getRenewalCount() + 1);
        checkRenewalCount(reservation);

        return ReservationRenewalResponse.from(reservation);
    }

    private static void checkRenewalTime(Reservation reservation) {
        if (LocalDateTime.now().isBefore(reservation.getEndAt().minusMinutes(30))) {
            throw new ExpiredRenewalTimeException();
        }
    }

    private void checkRenewalCount(Reservation reservation) {
        if (reservation.getRenewalCount() > 4) {
            throw new ReservationLimitExceededException();
        }
    }

    @Transactional
    public void deleteReservation(Long reservationId) {
        reservationRepository.findById(reservationId)
                .ifPresentOrElse(
                        reservation -> {
                            reservationRepository.deleteById(reservationId);
                            LibrarySeat librarySeat = librarySeatRepository.findById(reservation.getLibrarySeatId()).orElseThrow(LibrarySeatNotFoundException::new);
                            librarySeat.updateAvailable();
                        },
                        () -> { throw new ReservationNotFoundException(); }
                );
    }
}

