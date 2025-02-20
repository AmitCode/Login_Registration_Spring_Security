package com.spring.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    @NotEmpty(message = "User Name can't be null")
    private String userName;
    @NotEmpty(message = "User Email can't be null")
    @Email
    private String userEmail;
    @NotEmpty(message = "User Password can't be null")
    private String userPassword;
}
