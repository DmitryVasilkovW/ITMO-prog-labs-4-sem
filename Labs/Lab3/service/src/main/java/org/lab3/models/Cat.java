package org.lab3.models;

import java.time.LocalDate;
import java.util.List;

public record Cat(int id, String name, LocalDate birthDate, String breed, String color, Owner owner, List<Cat> friends) {}