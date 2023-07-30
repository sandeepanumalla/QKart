package com.example.qkart.service;

import com.example.qkart.dao.UserLoginRequest;
import com.example.qkart.dao.UserRegisterRequest;

public interface IUserService {

    void login(UserLoginRequest userLoginRequest) throws Exception;

    void logout();

    void register(UserRegisterRequest userRegisterRequest) throws Exception;
}
