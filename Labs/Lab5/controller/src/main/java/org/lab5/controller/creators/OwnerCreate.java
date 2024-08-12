package org.lab5.controller.creators;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record OwnerCreate(@NotBlank(message = "Name should not be blank") String name, @PastOrPresent(message = "Date is invalid") LocalDate birthDate) {}
