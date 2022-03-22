package com.example.demo.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentConfig.class);

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        LOGGER.info("inserting dummy data @ {}", Instant.now());
        return args -> {
            Student damba = new Student(
                    "Damba",
                    "dambak@live.com",
                    LocalDate.of(2001, Month.JUNE, 22)
            );
            Student keka = new Student(
                    "keka",
                    "keka@live.com",
                    LocalDate.of(2000, Month.MARCH, 20)
            );

            repository.saveAll(
                    List.of(keka, damba)
            );
        };
    }
}
