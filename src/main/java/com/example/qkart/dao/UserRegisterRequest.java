package com.example.qkart.dao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotNull(message = "Name is required")
    public String username;

    @NotNull(message = "Email is required")
    public String firstName;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    public String password;

    public String confirmPassword;
}
