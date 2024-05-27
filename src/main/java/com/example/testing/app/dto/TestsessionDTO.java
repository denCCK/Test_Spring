package com.example.testing.app.dto;


import com.example.testing.app.model.Grade;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TestsessionDTO {
    private Integer id;
    private String testsessionName;
    private String testsessionDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer testId;
    private LocalDate creationDate;
    private LocalDate lastChangeDate;

    //private Set<Grade> grades;

}

