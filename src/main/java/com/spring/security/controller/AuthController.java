package com.spring.security.controller;

import com.spring.security.dto.UserDto;
import com.spring.security.pojo.User;
import com.spring.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/HomePage")
    public String getHomePage(){
        return "Home_Page";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "Registration_Page";
    }

    @PostMapping("/register/registerNewUser")
    public String registerNewUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult,
                                  Model model){
        User oldUser = userService.findUserEmail(userDto.getUserEmail());
        if(oldUser != null){
            bindingResult.rejectValue("userEmail",null,"User already exist!...");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user",userDto);
            return "Registration_Page";
        }
        userService.registerUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/getAllRegisteredUsers")
    public String getAllRegisteredUsers(Model model) {
        List<UserDto> listOfUsers = userService.findAllRegisteredUser();
        model.addAttribute("users",listOfUsers);
        return "User_List";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
