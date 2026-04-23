package com.example.test.service;

import com.example.test.exeption.ResourceNotFoundException;
import com.example.test.modal.Patient;
import com.example.test.repository.PatientRepository;
import com.example.test.validation.PatientValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientValidator patientValidator;

    public PatientService(PatientRepository patientRepository,
                          PatientValidator patientValidator) {
        this.patientRepository = patientRepository;
        this.patientValidator = patientValidator;
    }

    public Patient create(Patient patient) {
        patientValidator.validateAndNormalize(patient);
        return patientRepository.save(patient);
    }

    public Patient update(Long id, Patient updated) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));

        existing.setName(updated.getName());
        existing.setGender(updated.getGender());
        existing.setDateOfBirth(updated.getDateOfBirth());

        patientValidator.validateAndNormalize(existing);
        return patientRepository.save(existing);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void delete(Long id) {
        Patient patient = getById(id);
        patientRepository.delete(patient);
    }
}
