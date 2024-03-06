package com.example.pnuunivmiryangcampus.repository;

import com.example.pnuunivmiryangcampus.domain.LibrarySeat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibrarySeatRepository extends JpaRepository<LibrarySeat, Long> {

    // Optional Type 고려
    @Query("select ls from LibrarySeat ls where ls.availability<> '사용가능'")
    List<LibrarySeat> findLibrarySeatsByUnavailability();

    LibrarySeat findBySeatNumber(int seatNumber);
}