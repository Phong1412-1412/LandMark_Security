package com.phong1412.productsapi_security.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;
    @Column(name = "username")
    private String username;
    private String password;
    private String email;
    private boolean account_non_locked;
    private String role;
}
