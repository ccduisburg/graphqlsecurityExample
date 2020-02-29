package com.concon.graphqlexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    @Nullable
    private int tokenversion;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
