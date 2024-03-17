package com.example.pnuunivmiryangcampus.librarySeat.service;

import com.example.pnuunivmiryangcampus.librarySeat.dto.LibrarySeatDto;
import com.example.pnuunivmiryangcampus.librarySeat.repository.LibrarySeatRepository;
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
    public List<LibrarySeatDto> getAvailableLibrarySeat() {
        return librarySeatRepository.findLibrarySeatsByAvailability()
                .stream()
                .map(LibrarySeatDto::from)
                .toList();
    }
}
