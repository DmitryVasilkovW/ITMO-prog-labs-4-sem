package org.lab4.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.lab4.app"})
public class Lab4Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Lab4Application.class, args);
    }
}