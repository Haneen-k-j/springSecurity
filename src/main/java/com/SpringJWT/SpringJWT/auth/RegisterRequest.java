package com.SpringJWT.SpringJWT.auth;

import com.SpringJWT.SpringJWT.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastName;
    private String password;
    private String email;
    private Role role;
}
