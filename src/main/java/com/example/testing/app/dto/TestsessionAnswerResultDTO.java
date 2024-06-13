package com.example.testing.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestsessionAnswerResultDTO {
    private Integer id;
    private Integer testsessionResultId;
    private Integer answerId;
    private Integer questionId;
    private String answerText;
    private byte[] answerImg;
    private Boolean isCorrect;
    private String complianceText;
    private byte[] complianceImg;
    private String answerFormula;
    private Boolean isFormula;
    private Boolean isComplianceFormula;


}

