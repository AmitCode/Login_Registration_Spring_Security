package com.spring.security.mapper;

import com.spring.security.dto.UserDto;
import com.spring.security.pojo.User;

public class UserMapperClass {
    public static UserDto mapToUserDtoEntity(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setUserPassword(userDto.getUserPassword());
        return userDto;
    }

    public static User mapToUserEntity(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setPassword(userDto.getUserPassword());
        return user;
    }
}
