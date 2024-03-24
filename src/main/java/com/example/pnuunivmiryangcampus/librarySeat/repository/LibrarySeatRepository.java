package com.example.pnuunivmiryangcampus.librarySeat.repository;

import com.example.pnuunivmiryangcampus.librarySeat.LibrarySeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibrarySeatRepository extends JpaRepository<LibrarySeat, Long> {

    @Query("select ls from LibrarySeat ls where ls.isDeleted = false and ls.availability = '사용가능'")
    Optional<List<LibrarySeat>> findLibrarySeatsByAvailability();

    @Query("select ls from LibrarySeat ls where ls.isDeleted = false and ls.id = :id")
    Optional<LibrarySeat> findById(Long id);
}