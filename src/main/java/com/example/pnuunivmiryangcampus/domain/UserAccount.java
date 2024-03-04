package com.example.pnuunivmiryangcampus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class UserAccount extends AuditingFields{

    @Column(nullable = false)
    private String profileNickname;

    @Column(nullable = false, length = 100)
    private String accountEmail;

    private String memo;

    protected UserAccount() {
    }

    private UserAccount(String profileNickname, String accountEmail, String memo) {
        this.profileNickname = profileNickname;
        this.accountEmail = accountEmail;
        this.memo = memo;
    }

    public UserAccount of(String profileNickname, String accountEmail, String memo) {
        return new UserAccount(profileNickname, accountEmail, memo);
    }
}
