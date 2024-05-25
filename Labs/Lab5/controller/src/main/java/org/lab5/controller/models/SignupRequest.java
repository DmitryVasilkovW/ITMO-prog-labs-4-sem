package org.lab5.controller.models;

import lombok.Data;
import org.lab5.dataAccess.models.Roles;

import java.time.LocalDate;

@Data
public class SignupRequest
{
    private String ownerName;
    private String email;
    private LocalDate birthdate;
    private String password;
    private Roles role;
}
