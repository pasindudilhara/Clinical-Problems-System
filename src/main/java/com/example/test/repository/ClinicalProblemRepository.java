package com.example.test.repository;

import com.example.test.modal.ClinicalProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalProblemRepository extends JpaRepository<ClinicalProblem, Long> {
    List<ClinicalProblem> findByPatientId(Long patientId);
}
