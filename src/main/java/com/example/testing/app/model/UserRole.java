package com.example.testing.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_role", schema = "public")
public class UserRole {
    @Id
    @Column(name = "user_role_id", nullable = false)
    private Short id;

    @Column(name = "user_role_name", nullable = false, length = 50)
    private String userRoleName;

}