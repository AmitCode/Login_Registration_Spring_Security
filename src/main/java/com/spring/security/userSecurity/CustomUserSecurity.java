package com.spring.security.userSecurity;

import com.spring.security.pojo.User;
import com.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.spring.security.config.SpringSecurity.passwordEncoder;

@Service
public class CustomUserSecurity implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public CustomUserSecurity(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = repository.findByUserEmail(usernameOrEmail);
        if (user != null){
            return new org.springframework.security.core.userdetails.User(user.getUserEmail(),user.getPassword(),
                    user.getRoleList().stream().map((role) -> new SimpleGrantedAuthority(role.getRoleName()))
                            .collect(Collectors.toList()));
        }else {
            throw  new UsernameNotFoundException("Invalid email or password");
        }

    }
}
