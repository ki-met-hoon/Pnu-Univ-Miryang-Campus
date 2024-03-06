package com.example.pnuunivmiryangcampus.service;

import com.example.pnuunivmiryangcampus.dto.ReservationDto;
import com.example.pnuunivmiryangcampus.repository.ReservationRepository;
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

    //추후에 findById method에 의해서 삭제될 수 있음, 상황을 고려
    public Long saveReservationAndReturnId(ReservationDto dto) {
        return reservationRepository.save(dto.toEntity()).getId();
    }
}
