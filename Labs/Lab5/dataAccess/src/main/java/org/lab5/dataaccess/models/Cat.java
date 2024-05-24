package org.lab5.dataaccess.models;

import java.time.LocalDate;
import java.util.List;

public record Cat
(
        int id,
        String name,
        LocalDate birthDate,
        String breed,
        String color,
        Integer ownerId,
        List<Cat> friends
) {}
