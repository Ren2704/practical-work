package com.example.demo.dao.entity;


import com.example.demo.dao.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString()

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.NOT_SELECTED;
}
