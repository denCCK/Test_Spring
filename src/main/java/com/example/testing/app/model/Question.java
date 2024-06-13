package com.example.testing.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Integer id;

    @Column(name = "question_name", nullable = false, length = 50)
    private String questionName;

    @Column(name = "question_description", nullable = false)
    private String questionDescription;

    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @Column(name = "last_change_date", nullable = false)
    private Timestamp lastChangeDate;

    @Column(name = "question_point", nullable = false)
    private Float questionPoint;

    @Column(name = "answers_type", nullable = false, length = 50)
    private String answersType;

    @Column(name = "difficulty", nullable = false)
    private Short difficulty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "question_type", nullable = false, length = 50)
    private String questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new LinkedHashSet<>();

    @Column(name = "theme", nullable = false, length = 50)
    private String theme;

}
