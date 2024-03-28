package com.example.pnuunivmiryangcampus.support;

import com.example.pnuunivmiryangcampus.config.security.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return Optional.of(customUserDetails.getId().toString());
    }
}
