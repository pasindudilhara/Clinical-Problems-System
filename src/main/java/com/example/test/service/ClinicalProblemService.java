package com.example.test.service;

import com.example.test.exeption.ResourceNotFoundException;
import com.example.test.modal.ClinicalProblem;
import com.example.test.modal.Patient;
import com.example.test.repository.ClinicalProblemRepository;
import com.example.test.repository.PatientRepository;
import com.example.test.validation.SnomedValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicalProblemService {

    private final ClinicalProblemRepository clinicalProblemRepository;
    private final PatientRepository patientRepository;
    private final SnomedValidator snomedValidator;

    public ClinicalProblemService(ClinicalProblemRepository clinicalProblemRepository,
                                  PatientRepository patientRepository,
                                  SnomedValidator snomedValidator) {
        this.clinicalProblemRepository = clinicalProblemRepository;
        this.patientRepository = patientRepository;
        this.snomedValidator = snomedValidator;
    }

    public ClinicalProblem createForPatient(Long patientId, ClinicalProblem problem) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + patientId));

        snomedValidator.validate(problem.getCoding());
        problem.setPatient(patient);

        return clinicalProblemRepository.save(problem);
    }

    public ClinicalProblem update(Long patientId, Long problemId, ClinicalProblem updated) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + patientId));

        ClinicalProblem existing = clinicalProblemRepository.findById(problemId)
                .orElseThrow(() -> new ResourceNotFoundException("Clinical problem not found: " + problemId));

        if (!existing.getPatient().getId().equals(patient.getId())) {
            throw new ResourceNotFoundException("Clinical problem " + problemId + " does not belong to patient " + patientId);
        }

        snomedValidator.validate(updated.getCoding());
        existing.setCoding(updated.getCoding());

        return clinicalProblemRepository.save(existing);
    }

    public List<ClinicalProblem> findByPatientId(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new ResourceNotFoundException("Patient not found: " + patientId);
        }
        return clinicalProblemRepository.findByPatientId(patientId);
    }

    public Optional<ClinicalProblem> findById(Long problemId) {
        return clinicalProblemRepository.findById(problemId);
    }

    public void delete(Long patientId, Long problemId) {
        ClinicalProblem existing = clinicalProblemRepository.findById(problemId)
                .orElseThrow(() -> new ResourceNotFoundException("Clinical problem not found: " + problemId));

        if (!existing.getPatient().getId().equals(patientId)) {
            throw new ResourceNotFoundException("Clinical problem " + problemId + " does not belong to patient " + patientId);
        }

        clinicalProblemRepository.delete(existing);
    }
}