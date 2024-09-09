package com.hariprasath.springsecuritywithjwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    private int id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING) // Store enum as string or handle conversion
    private UserRole role;
}
