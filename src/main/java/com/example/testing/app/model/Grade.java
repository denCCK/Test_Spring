package com.example.testing.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "testsession"})
@Getter
@Setter
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "testsession_id")
    private Testsession testsession;

    @Column(name = "grade_name", nullable = false, length = 50)
    private String gradeName;

    @Column(name = "grade_value", nullable = false)
    private Float gradeValue;

}