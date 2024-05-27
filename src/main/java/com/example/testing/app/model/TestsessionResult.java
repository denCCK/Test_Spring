package com.example.testing.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "testsession_result")
public class TestsessionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testsession_result_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "testsession_id")
    private Testsession testsession;

    @Column(name = "testsession_result_name", nullable = false, length = 50)
    private String testsessionResultName;

    @Column(name = "testsession_result_surname", nullable = false, length = 50)
    private String testsessionResultSurname;

    @Column(name = "point", nullable = false)
    private Float point;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "testsessionResult", cascade = CascadeType.ALL)
    private Set<TestsessionAnswerResult> testsessionAnswerResults = new LinkedHashSet<>();

    @OneToMany(mappedBy = "testsessionResult", cascade = CascadeType.ALL)
    private Set<TestsessionQuestionResult> testsessionQuestionResults = new LinkedHashSet<>();

}