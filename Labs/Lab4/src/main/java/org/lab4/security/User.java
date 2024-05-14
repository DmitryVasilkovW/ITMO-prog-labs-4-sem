package org.lab4.security;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
}
