package com.example.testing.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestsessionQuestionResultDTO {
    private Integer id;
    private Integer testsessionResultId;
    private Integer questionId;
    private Boolean isCorrect;
    private Float point;

}

