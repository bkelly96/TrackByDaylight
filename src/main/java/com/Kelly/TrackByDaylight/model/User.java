package com.Kelly.TrackByDaylight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints =
        {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@Data @NoArgsConstructor @AllArgsConstructor
public class User{

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
