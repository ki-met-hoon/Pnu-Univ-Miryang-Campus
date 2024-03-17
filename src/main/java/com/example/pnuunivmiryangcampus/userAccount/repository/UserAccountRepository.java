package com.example.pnuunivmiryangcampus.userAccount.repository;

import com.example.pnuunivmiryangcampus.userAccount.UserAccount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findBySub(String sub);
}