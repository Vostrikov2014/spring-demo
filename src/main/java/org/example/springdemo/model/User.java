package org.example.springdemo.model;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    private int id;
    private String username;
    private String password;
    private String authority;
}
