package com.example.pnuunivmiryangcampus.userAccount;

import com.example.pnuunivmiryangcampus.AuditingFields;
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
public class UserAccount extends AuditingFields {

    @Column(nullable = false)
    private String profileNickname;

    @Column(nullable = false, length = 100)
    private String accountEmail;

    @Column(nullable = false)
    private String sub;

    @Column
    private String memo;

    @Column(nullable = false, length = 100, updatable = false)
    private String createdBy;

    protected UserAccount() {
    }

    private UserAccount(String profileNickname, String accountEmail, String sub, String memo, String createdBy) {
        this.profileNickname = profileNickname;
        this.accountEmail = accountEmail;
        this.sub = sub;
        this.memo = memo;
        this.createdBy = createdBy;
    }

    public static UserAccount of(String profileNickname, String accountEmail, String sub, String memo,
                                 String createdBy) {
        return new UserAccount(profileNickname, accountEmail, sub, memo, createdBy);
    }
}
