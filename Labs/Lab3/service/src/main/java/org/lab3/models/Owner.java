package org.lab3.models;

import java.time.LocalDate;
import java.util.List;

public record Owner(int id, String name, LocalDate birthDate, List<Cat> cats) {}