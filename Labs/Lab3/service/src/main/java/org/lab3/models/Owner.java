package org.lab3.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Owner
{
    private int id;
    private String name;
    private LocalDate birthDate;
    private List<Cat> cats;
}