package com.example.test.validation;


import com.example.test.exeption.ValidationException;
import com.example.test.modal.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class PatientValidator {

    private static final Set<String> ALLOWED_GENDERS =
            Set.of("male", "female", "other", "unknown");

    public void validateAndNormalize(Patient patient) {
        if (patient == null) {
            throw new ValidationException("Patient is required");
        }

        if (patient.getName() == null || patient.getName().trim().isEmpty()) {
            throw new ValidationException("Patient name must not be blank");
        }
        patient.setName(patient.getName().trim());

        if (patient.getGender() == null || patient.getGender().trim().isEmpty()) {
            throw new ValidationException("Patient gender is required");
        }

        String normalizedGender = patient.getGender().trim().toLowerCase();
        if (!ALLOWED_GENDERS.contains(normalizedGender)) {
            throw new ValidationException("Patient gender must be one of: male, female, other, unknown");
        }
        patient.setGender(normalizedGender);

        LocalDate dob = patient.getDateOfBirth();
        if (dob == null) {
            throw new ValidationException("Patient dateOfBirth is required");
        }
        if (!dob.isBefore(LocalDate.now())) {
            throw new ValidationException("Patient dateOfBirth must be in the past");
        }
    }
}
