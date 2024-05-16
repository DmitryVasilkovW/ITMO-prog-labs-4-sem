package org.lab4.app.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
public class OwnerDao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "roles")
    @ColumnTransformer(
            write = "?::int"
    )
    private Roles role;


    @OneToMany(mappedBy = "ownerId")
    private List<CatDao> cats;
}