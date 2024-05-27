package com.example.testing.app.service;

import com.example.testing.app.dto.GradeDTO;
import com.example.testing.app.model.Grade;
import com.example.testing.app.model.Testsession;
import com.example.testing.app.repository.GradeRepository;
import com.example.testing.app.repository.TestsessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final TestsessionRepository testsessionRepository;

    public GradeService(GradeRepository gradeRepository, TestsessionRepository testsessionRepository) {
        this.gradeRepository = gradeRepository;
        this.testsessionRepository = testsessionRepository;
    }

    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<GradeDTO> getGradeById(Integer id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        return grade.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = convertToEntity(gradeDTO);
        return convertToDto(gradeRepository.save(grade));
    }

    public ResponseEntity<GradeDTO> updateGrade(Integer id, GradeDTO gradeDTO) {
        Optional<Grade> existingGrade = gradeRepository.findById(id);
        if (existingGrade.isPresent()) {
            Grade grade = convertToEntity(gradeDTO);
            grade.setId(id);
            return ResponseEntity.ok(convertToDto(gradeRepository.save(grade)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteGrade(Integer id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        if (grade.isPresent()) {
            gradeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<GradeDTO> getGradesByTestsessionId(Integer testsessionId) {
        return gradeRepository.findByTestsessionId(testsessionId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Void> deleteGradesByTestsessionId(Integer testsessionId) {
        List<Grade> grades = gradeRepository.findByTestsessionId(testsessionId);
        if (!grades.isEmpty()) {
            gradeRepository.deleteAll(grades);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> updateGradesByTestsessionId(Integer testsessionId, List<GradeDTO> gradeDTOs) {
        List<Grade> existingGrades = gradeRepository.findByTestsessionId(testsessionId);
        if (!existingGrades.isEmpty()) {
            gradeRepository.deleteAll(existingGrades);
        }
        List<Grade> newGrades = gradeDTOs.stream().map(this::convertToEntity).collect(Collectors.toList());
        gradeRepository.saveAll(newGrades);
        return ResponseEntity.noContent().build();
    }

    private GradeDTO convertToDto(Grade grade) {
        GradeDTO dto = new GradeDTO();
        dto.setId(grade.getId());
        dto.setGradeName(grade.getGradeName());
        dto.setGradeValue(grade.getGradeValue());
        dto.setTestsessionId(grade.getTestsession().getId());
        return dto;
    }

    private Grade convertToEntity(GradeDTO dto) {
        Grade grade = new Grade();
        grade.setId(dto.getId());
        grade.setGradeName(dto.getGradeName());
        grade.setGradeValue(dto.getGradeValue());
        grade.setTestsession(testsessionRepository.findById(dto.getTestsessionId()).orElse(null));
        return grade;
    }
}


