package com.example.pnuunivmiryangcampus.reservation.repository;

import com.example.pnuunivmiryangcampus.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r where r.isDeleted = false and r.userAccountId = :userId")
    Optional<Reservation> findByUserAccountId(Long userId);

    @Query("select r from Reservation r where r.isDeleted = false and r.id = :id")
    Optional<Reservation> findById(Long id);
}