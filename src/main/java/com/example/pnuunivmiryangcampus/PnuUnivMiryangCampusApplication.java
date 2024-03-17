package com.example.pnuunivmiryangcampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class PnuUnivMiryangCampusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PnuUnivMiryangCampusApplication.class, args);
    }

}
