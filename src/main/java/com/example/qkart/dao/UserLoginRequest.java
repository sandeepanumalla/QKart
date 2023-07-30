package com.example.qkart.dao;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    @NotNull(message = "Please provide username")
    public String username;

    @NotNull(message = "Please provide password")
    public String password;
}
