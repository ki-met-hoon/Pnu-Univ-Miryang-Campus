package com.example.pnuunivmiryangcampus.dto;

import com.example.pnuunivmiryangcampus.domain.UserAccount;
import java.io.Serializable;
import java.time.LocalDateTime;

public record UserAccountDto(
        Long id,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        boolean isDeleted,
        String profileNickname,
        String accountEmail,
        String sub,
        String memo
        ) implements Serializable {

    public static UserAccountDto of(Long id, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, boolean isDeleted, String profileNickname,
                                    String accountEmail,
                                    String sub,
                                    String memo) {

        return new UserAccountDto(id, createdAt, createdBy, modifiedAt, modifiedBy, isDeleted, profileNickname,
                accountEmail, sub, memo);
    }

    public static UserAccountDto of(String profileNickname, String accountEmail, String sub) {
        return new UserAccountDto(null, null, profileNickname, null, null, false, profileNickname, accountEmail, sub, null);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.isDeleted(),
                entity.getProfileNickname(),
                entity.getAccountEmail(),
                entity.getSub(),
                entity.getMemo()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                profileNickname,
                accountEmail,
                sub,
                memo,
                createdBy
        );
    }
}