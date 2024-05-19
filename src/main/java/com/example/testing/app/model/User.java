package com.example.testing.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_ts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_login", nullable = false, length = 50)
    private String userlogin;

    @Column(name = "user_password", nullable = false, length = 50)
    private String userpassword;

    @Column(name = "user_name", nullable = false, length = 50)
    private String username;

    @Column(name = "user_surname", nullable = false, length = 50)
    private String usersurname;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userrole;

}
