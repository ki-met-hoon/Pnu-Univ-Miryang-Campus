package com.example.pnuunivmiryangcampus.reservation.repository;

import com.example.pnuunivmiryangcampus.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}