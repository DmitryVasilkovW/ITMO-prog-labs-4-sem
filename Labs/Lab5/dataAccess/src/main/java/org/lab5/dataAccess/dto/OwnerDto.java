package org.lab5.dataAccess.dto;

import org.lab5.dataAccess.models.Roles;

import java.time.LocalDate;
import java.util.List;

public record OwnerDto
        (
                Long id,
                String name,
                String password,
                String email,
                LocalDate birthDate,
                Roles role,
                List<CatDto> cats
        ) {}
