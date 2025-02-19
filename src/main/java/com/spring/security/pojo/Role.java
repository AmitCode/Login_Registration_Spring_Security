package com.spring.security.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLES_INFO_TABLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @NotNull(message = "Role Name can't be null")
    @Column(unique = true)
    private String roleName;
    @ManyToMany(mappedBy = "roleList")
    private List<User> roleList = new ArrayList<>();
}
