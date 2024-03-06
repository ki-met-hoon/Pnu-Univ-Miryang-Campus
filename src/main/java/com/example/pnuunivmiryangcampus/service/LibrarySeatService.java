package com.example.pnuunivmiryangcampus.service;

import com.example.pnuunivmiryangcampus.domain.LibrarySeat;
import com.example.pnuunivmiryangcampus.dto.LibrarySeatDto;
import com.example.pnuunivmiryangcampus.repository.LibrarySeatRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LibrarySeatService {

    private final LibrarySeatRepository librarySeatRepository;

    @Transactional(readOnly = true)
    public List<LibrarySeatDto> getUnavailableLibrarySeat() {
        return librarySeatRepository.findLibrarySeatsByUnavailability()
                .stream()
                .map(LibrarySeatDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public Long getLibrarySeatId(int seatNumber) {
        //isDeleted가 true인 값은 제외하는 로직에 대해 생각
        return librarySeatRepository.findBySeatNumber(seatNumber).getId();
    }
}
