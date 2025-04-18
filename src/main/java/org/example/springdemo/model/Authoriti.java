package org.example.springdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Authorities")
public class Authoriti {

    @Id
    private int id;
    private String username;
    private String authority;
}
