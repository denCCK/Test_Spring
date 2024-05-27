package com.example.testing.app.repository;


import com.example.testing.app.model.Grade;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findByTestsessionId(Integer testsessionId);

    void deleteByTestsessionId(Integer testsessionId);

}

