package org.lab3.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class OwnerDao
{
    private int id;
    private String name;
    private LocalDate birthDate;
    private List<CatDao> cats;
}