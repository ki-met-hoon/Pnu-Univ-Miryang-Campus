package com.example.pnuunivmiryangcampus.repository;

import com.example.pnuunivmiryangcampus.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}