package com.example.pnuunivmiryangcampus.userAccount.repository;

import com.example.pnuunivmiryangcampus.userAccount.UserAccount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select ua from UserAccount ua where ua.isDeleted = false and ua.sub = :sub")
    Optional<UserAccount> findBySub(String sub);
}