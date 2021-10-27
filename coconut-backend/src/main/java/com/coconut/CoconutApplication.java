package com.coconut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoconutApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoconutApplication.class, args);
    }

}
