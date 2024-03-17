package com.example.pnuunivmiryangcampus;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    //원래는 Spring Security의 ContextHolder안에 들어있는 Principal의 name값으로 매핑
    //지금은 Security를 구현하지 않았기 때문에 nullable = true로 변경
    //Security 구현 후 nullable = false로 재변경
    @Column(length = 100, updatable = false)
    private String createdBy;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column
    private String modifiedBy;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @PrePersist
    public void prePersist() {
        this.modifiedAt = null;
    }
}
