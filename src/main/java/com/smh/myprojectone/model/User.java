package com.smh.myprojectone.model;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;
    @NotEmpty
    @Column(nullable = false)
    private String LastName;
    @NotEmpty
    @Column(nullable = false, unique = true)
    @Email(message = "{error.invalid_email}")
    private String email;
    @NotEmpty
    private String password;

    @ManyToMany
    @JoinTable
    private List<Role> roles;

    public User() {

    }
    public User(User user) {
        this.firstName = user.getFirstName();
        LastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
}
