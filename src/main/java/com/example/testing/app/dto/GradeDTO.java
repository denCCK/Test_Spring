package com.example.testing.app.dto;

import lombok.*;

// GradeDTO.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private Integer id;
    private String gradeName;
    private Float gradeValue;
    private Integer testsessionId;
}

