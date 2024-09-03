package com.hariprasath.springsecuritywithjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityRequest {

    private String username;
    private String password;
    private UserRole role;
}
