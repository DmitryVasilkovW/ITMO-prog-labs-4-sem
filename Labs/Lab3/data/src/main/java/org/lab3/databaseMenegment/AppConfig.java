package org.lab3.databaseMenegment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig
{
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:6432/postgres");
        dataSource.setUsername("lab");
        dataSource.setPassword("lab3");

        return new NamedParameterJdbcTemplate(dataSource);
    }
}