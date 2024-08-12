package org.lab5.controller.creators;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CatCreate(@NotBlank(message = "Name should not be blank") String name, @PastOrPresent(message = "Date is invalid") LocalDate birthDate, @NotBlank(message = "Breed should not be blank") String breed, @NotBlank(message = "Colour should not be blank") String colour, int ownerId){}
