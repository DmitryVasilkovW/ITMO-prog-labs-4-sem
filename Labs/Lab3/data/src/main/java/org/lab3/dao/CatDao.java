package org.lab3.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class CatDao
{
    private int id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private String color;
    private Integer ownerId;
    private List<CatDao> friends;
}