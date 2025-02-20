package com.spring.security.service;

import com.spring.security.dto.UserDto;
import com.spring.security.pojo.User;

import java.util.List;

public interface UserService {
    public void registerUser(UserDto userDto);
    public User findUserEmail(String userEmail);
    public List<UserDto> findAllRegisteredUser();
}
