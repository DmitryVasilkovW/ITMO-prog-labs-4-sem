package org.lab4.app.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cats")
@Data
@NoArgsConstructor
public class CatDao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column
    private String breed;

    @Column
    private String color;

    @Column(name = "owner_id")
    private Integer ownerId;

    @ManyToMany
    @JoinTable(
            name = "cats_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<CatDao> friends;
}