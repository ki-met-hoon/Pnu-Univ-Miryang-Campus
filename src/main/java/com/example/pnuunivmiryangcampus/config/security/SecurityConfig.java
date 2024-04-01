package com.example.pnuunivmiryangcampus.config.security;

import com.example.pnuunivmiryangcampus.support.token.OauthOIDCHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final OauthOIDCHelper oauthOIDCHelper;
    private final CustomUserDetailsService customUserDetailsService;

    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder.addFilter(new JwtAuthenticationFilter(authenticationManager, oauthOIDCHelper, customUserDetailsService));
            super.configure(builder);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final String[] swaggerPermitUrls = {
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html"
        };

        http
                //.csrf(csrf -> csrf.disable())와 아래의 형태가 동일
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                //.apply(new CustomSecurityFilterManager())가 아래의 코드로 변경됨
                .with(new CustomSecurityFilterManager(), Customizer.withDefaults())
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));

        http
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers("/library/reservation/**").hasRole("USER")
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers(swaggerPermitUrls).permitAll()
                                .requestMatchers("/library/seats").permitAll()
                                .requestMatchers("/auth/**").permitAll());

        return http.build();
    }
}
