package org.lab3.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Cat
{
    private int id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private String color;
    private Owner owner;
    private List<Cat> friends;
}
