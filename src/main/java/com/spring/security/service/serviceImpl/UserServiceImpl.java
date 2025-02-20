package com.spring.security.service.serviceImpl;

import com.spring.security.dto.UserDto;
import com.spring.security.mapper.UserMapperClass;
import com.spring.security.pojo.Role;
import com.spring.security.pojo.User;
import com.spring.security.repository.RoleRepository;
import com.spring.security.repository.UserRepository;
import com.spring.security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserDto userDto) {
        User user = UserMapperClass.mapToUserEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRoleName("ROLE_ADMIN");
        //return UserMapperClass.mapToUserDtoEntity(user);
        if (role == null){
            role = roleCheck();
        }
        user.setRoleList(Arrays.asList(role));
        repository.save(user);
    }

    private Role roleCheck(){
        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserEmail(String userEmail) {
        User user = repository.findByUserEmail(userEmail);
        return user;
    }

    @Override
    public List<UserDto> findAllRegisteredUser() {
        List<User> userList = repository.findAll();
        List<UserDto> listOfUsers = new ArrayList<>();
        for (User user : userList){
            listOfUsers.add(UserMapperClass.mapToUserDtoEntity(user));
        }
        return listOfUsers;
    }
}
