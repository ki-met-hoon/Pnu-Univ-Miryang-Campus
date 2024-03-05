package com.example.pnuunivmiryangcampus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class Reservation extends AuditingFields {

    @Column(nullable = false)
    private Long userAccountId;

    @Column(nullable = false)
    private Long librarySeatId;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    protected Reservation() {
    }

    private Reservation(Long userAccountId, Long librarySeatId, LocalDateTime startAt, LocalDateTime endAt) {
        this.userAccountId = userAccountId;
        this.librarySeatId = librarySeatId;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Reservation of(Long userAccountId, Long librarySeatId, LocalDateTime startAt, LocalDateTime endAt) {
        return new Reservation(userAccountId, librarySeatId, startAt, endAt);
    }
}
