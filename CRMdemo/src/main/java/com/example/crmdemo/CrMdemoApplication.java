package com.example.crmdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrMdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrMdemoApplication.class, args);
    }

}
