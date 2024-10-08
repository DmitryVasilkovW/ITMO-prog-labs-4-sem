package org.lab5.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.lab5")
@EntityScan(basePackages = "org.lab5")
@SpringBootApplication(scanBasePackages = {"org.lab5"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}