package com.example.pnuunivmiryangcampus.config.security;

import com.example.pnuunivmiryangcampus.userAccount.UserAccount;
import com.example.pnuunivmiryangcampus.userAccount.repository.UserAccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String sub) throws UsernameNotFoundException {
        Optional<UserAccount> findUser = userAccountRepository.findBySub(sub);

        return findUser.map(CustomUserDetails::new).orElseThrow();
    }
}
