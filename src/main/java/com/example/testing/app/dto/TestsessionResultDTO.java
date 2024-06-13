package com.example.testing.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TestsessionResultDTO {
    private Integer id;
    private Integer testsessionId;
    private String testsessionResultName;
    private String testsessionResultSurname;
    private Float point;
    private Timestamp startDate;
    private Timestamp endDate;
}

