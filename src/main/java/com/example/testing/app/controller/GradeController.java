package com.example.testing.app.controller;

import com.example.testing.app.dto.GradeDTO;
import com.example.testing.app.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Integer id) {
        return gradeService.getGradeById(id);
    }

    @PostMapping
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO) {
        GradeDTO createdGrade = gradeService.createGrade(gradeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Integer id, @RequestBody GradeDTO gradeDTO) {
        return gradeService.updateGrade(id, gradeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Integer id) {
        return gradeService.deleteGrade(id);
    }

    @GetMapping("/testsession/{testsessionId}")
    public ResponseEntity<List<GradeDTO>> getGradesByTestsessionId(@PathVariable Integer testsessionId) {
        return ResponseEntity.ok(gradeService.getGradesByTestsessionId(testsessionId));
    }

    @DeleteMapping("/testsession/{testsessionId}")
    public ResponseEntity<Void> deleteGradesByTestsessionId(@PathVariable Integer testsessionId) {
        return gradeService.deleteGradesByTestsessionId(testsessionId);
    }

    @PutMapping("/testsession/{testsessionId}")
    public ResponseEntity<Void> updateGradesByTestsessionId(@PathVariable Integer testsessionId, @RequestBody List<GradeDTO> gradeDTOs) {
        return gradeService.updateGradesByTestsessionId(testsessionId, gradeDTOs);
    }
}

