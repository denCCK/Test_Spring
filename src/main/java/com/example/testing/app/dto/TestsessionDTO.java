package com.example.testing.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TestsessionDTO {
    private Integer id;
    private String testsessionName;
    private String testsessionDescription;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer testId;
    private Timestamp creationDate;
    private Timestamp lastChangeDate;
    private Integer questionsCount;
    private Timestamp testsessionTime;;
}

