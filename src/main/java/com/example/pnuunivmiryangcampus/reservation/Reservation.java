package com.example.pnuunivmiryangcampus.reservation;

import com.example.pnuunivmiryangcampus.AuditingFields;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;


@Getter
@ToString(callSuper = true)
@SQLDelete(sql = "update reservation set is_deleted = true, modified_at = current_time, modified_by = user_account_id where id  = ?")
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

    @Column(nullable = false)
    private int renewalCount;

    protected Reservation() {
    }

    private Reservation(Long userAccountId, Long librarySeatId, LocalDateTime startAt, LocalDateTime endAt, int renewalCount) {
        this.userAccountId = userAccountId;
        this.librarySeatId = librarySeatId;
        this.startAt = startAt;
        this.endAt = endAt;
        this.renewalCount = renewalCount;
    }

    public static Reservation of(Long userAccountId, Long librarySeatId, LocalDateTime startAt, LocalDateTime endAt, int renewalCount) {
        return new Reservation(userAccountId, librarySeatId, startAt, endAt, renewalCount);
    }

    public void update(LocalDateTime endAt, int renewalCount) {
        this.endAt = endAt;
        this.renewalCount = renewalCount;
    }
}
