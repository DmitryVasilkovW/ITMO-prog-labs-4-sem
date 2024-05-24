package org.lab5.dataaccess.models;

import java.time.LocalDate;
import java.util.List;

public record Owner
(
        Long id,
        String name,
        String password,
        String email,
        LocalDate birthDate,
        Roles role,
        List<Cat> cats
) {}
