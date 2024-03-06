package com.example.pnuunivmiryangcampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PnuUnivMiryangCampusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PnuUnivMiryangCampusApplication.class, args);
    }

}
