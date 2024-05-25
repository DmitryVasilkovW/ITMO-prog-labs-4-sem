package org.lab5.ownerDomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.lab5.dataAccess.repositories")
@EntityScan(basePackages = "org.lab5.dataAccess")
@SpringBootApplication(scanBasePackages = {"org.lab5.ownerDomain"})
public class OwnerApplication
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = SpringApplication.run(OwnerApplication.class, args);
    }
}
