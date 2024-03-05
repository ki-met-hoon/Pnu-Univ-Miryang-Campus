package com.example.pnuunivmiryangcampus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class LibrarySeat extends AuditingFields{

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false, length = 16)
    private String availability;

    protected LibrarySeat() {
    }

    private LibrarySeat(int seatNumber, String availability) {
        this.seatNumber = seatNumber;
        this.availability = availability;
    }

    public static LibrarySeat of(int seatNumber, String availability) {
        return new LibrarySeat(seatNumber, availability);
    }
}