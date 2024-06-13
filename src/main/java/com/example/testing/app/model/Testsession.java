package com.example.testing.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "testsession")
public class Testsession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testsession_id", nullable = false)
    private Integer id;

    @Column(name = "testsession_name", nullable = false)
    private String testsessionName;

    @Column(name = "testsession_description", nullable = false)
    private String testsessionDescription;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @Column(name = "last_change_date", nullable = false)
    private Timestamp lastChangeDate;

    @OneToMany(mappedBy = "testsession", cascade = CascadeType.ALL)
    private Set<Grade> grades = new LinkedHashSet<>();

    @OneToMany(mappedBy = "testsession", cascade = CascadeType.ALL)
    private Set<TestsessionResult> testsessionResults = new LinkedHashSet<>();

    @Column(name = "questions_count")
    private Integer questionsCount;

    @Column(name = "testsession_time")
    private Timestamp testsessionTime;

}
