package org.lab5.catDomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.lab5.dataAccess.repositories")
@EntityScan(basePackages = "org.lab5.dataAccess")
@SpringBootApplication(scanBasePackages = {"org.lab5.catDomain"})
public class CatApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CatApplication.class, args);
    }
}

