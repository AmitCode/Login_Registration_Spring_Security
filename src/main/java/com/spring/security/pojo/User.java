package com.spring.security.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS_INFO_TABLE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull(message = "User Name can't be null")
    private String userName;
    @NotNull(message = "User Email can't be null")
    @Column(unique = true)
    @Email
    private String userEmail;
    @NotNull(message = "User Password can't be null")
    private String Password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //FetchType.EAGER whenever we load User entity roles associated with it will also be loaded.
    //cascade = CascadeType.ALL Any operation that will perform on User Entity
    // will also be applicable to Role Entity.
    private List<Role> roleList = new ArrayList<>();
    @JoinTable(
            name = "USER_ROLE_INFO",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName="userId")},
            inverseJoinColumns = {@JoinColumn (name = "role_id",referencedColumnName = "roleId")}
    )
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
}
