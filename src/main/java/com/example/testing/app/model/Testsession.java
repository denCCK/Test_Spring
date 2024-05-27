package com.example.testing.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
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
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "last_change_date", nullable = false)
    private LocalDate lastChangeDate;

    @OneToMany(mappedBy = "testsession", cascade = CascadeType.ALL)
    private Set<Grade> grades = new LinkedHashSet<>();

    @OneToMany(mappedBy = "testsession", cascade = CascadeType.ALL)
    private Set<TestsessionResult> testsessionResults = new LinkedHashSet<>();

}
