package com.example.pnuunivmiryangcampus.config.security;

import com.example.pnuunivmiryangcampus.auth.OIDCDecodePayload;
import com.example.pnuunivmiryangcampus.support.token.OauthOIDCHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";

    private final OauthOIDCHelper oauthOIDCHelper;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, OauthOIDCHelper oauthOIDCHelper,
                                   CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.oauthOIDCHelper = oauthOIDCHelper;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = resolveToken(request);

        if (token != null) {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String rawHeader = request.getHeader(AUTH_HEADER);

        if (rawHeader != null
                && rawHeader.length() > BEARER.length()
                && rawHeader.startsWith(BEARER)) {
            return rawHeader.substring(BEARER.length());
        }

        return null;
    }

    private Authentication getAuthentication(String token) {
        OIDCDecodePayload oidcDecodePayload = oauthOIDCHelper.getKakaoOIDCDecodePayload(token);
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(oidcDecodePayload.sub());

        return new UsernamePasswordAuthenticationToken(customUserDetails, customUserDetails.getPassword(),
                customUserDetails.getAuthorities());
    }
}
