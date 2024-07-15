package com.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;
    
    @Column(name = "name", unique = true)
    private String name;
    
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "roles")
    private String roles = "ROLE_USER";

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    };

}