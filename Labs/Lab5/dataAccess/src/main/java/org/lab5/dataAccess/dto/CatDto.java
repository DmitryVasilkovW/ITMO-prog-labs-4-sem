package org.lab5.dataAccess.dto;

import java.time.LocalDate;
import java.util.List;

public record CatDto
        (
                int id,
                String name,
                LocalDate birthDate,
                String breed,
                String color,
                Integer ownerId,
                List<CatDto> friends
        ) {}
