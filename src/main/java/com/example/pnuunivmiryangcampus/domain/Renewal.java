package com.example.pnuunivmiryangcampus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class Renewal extends AuditingFields {

    @Column(nullable = false)
    private Long reservationId;

    @Column(nullable = false)
    private int renewalCount;

    protected Renewal() {
    }

    private Renewal(Long reservationId, int renewalCount) {
        this.reservationId = reservationId;
        this.renewalCount = renewalCount;
    }

    public static Renewal of(Long reservationId, int renewalCount) {
        return new Renewal(reservationId, renewalCount);
    }
}
