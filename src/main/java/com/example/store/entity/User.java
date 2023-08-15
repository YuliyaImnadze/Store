package com.example.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity(name = "STORE_USER")
@Table(name = "user_store")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;  // у одного юзера 1 роль, у одной роли много юзеров


}