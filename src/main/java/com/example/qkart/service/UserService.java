package com.example.qkart.service;

import com.example.qkart.dao.UserLoginRequest;
import com.example.qkart.dao.UserRegisterRequest;
import com.example.qkart.model.User;
import com.example.qkart.repository.IUserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public UserService(IUserRepository userRepository, ModelMapper modelMapper, Validator validator) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }


    @Override
    public void login(UserLoginRequest userLoginRequest) throws Exception {
        Set<ConstraintViolation<UserLoginRequest>> violations = validator.validate(userLoginRequest);
        String username = userLoginRequest.username;
        if(!violations.isEmpty()) {
            for(ConstraintViolation<UserLoginRequest> violation: violations) {
                String fieldName = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                throw new ConstraintViolationException(message, violations);
            }
        }

        Optional<User> user = userRepository.finduserByUsername(username);
        if(user.isEmpty()) {
            throw new Exception();
        }
        if(!user.get().getPassword().equals(userLoginRequest.password)) {
            throw new Exception();
        }
    }

    @Override
    public void logout() {

    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) throws Exception {
        Set<ConstraintViolation<UserRegisterRequest>> violations = validator.validate(userRegisterRequest);

        if(!violations.isEmpty()) {
            for(ConstraintViolation<UserRegisterRequest> violation: violations) {
                String fieldName = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                throw new ConstraintViolationException(message, violations);
            }
        }


        User user = modelMapper.map(userRegisterRequest, User.class);
        user.setDateCreated(new Date());
        userRepository.save(user);
    }
}
