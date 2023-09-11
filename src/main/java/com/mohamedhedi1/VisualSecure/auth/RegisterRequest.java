package com.mohamedhedi1.VisualSecure.auth;

import com.mohamedhedi1.VisualSecure.permission.Permission;
import com.mohamedhedi1.VisualSecure.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Collection<Permission> permissions = new HashSet<>();


}