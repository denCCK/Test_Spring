package com.example.testing.app.repository;

import com.example.testing.app.model.Testsession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestsessionRepository extends JpaRepository<Testsession, Integer> {
}
