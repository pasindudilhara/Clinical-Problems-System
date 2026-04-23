package com.example.test.validation;

import com.example.test.exeption.ValidationException;
import com.example.test.modal.Coding;
import org.springframework.stereotype.Component;

@Component
public class SnomedValidator {

    public static final String SNOMED_SYSTEM = "http://snomed.info/sct";

    public void validate(Coding coding) {
        if (coding == null) {
            throw new ValidationException("Clinical problem coding is required");
        }

        if (!SNOMED_SYSTEM.equals(coding.getSystem())) {
            throw new ValidationException("coding.system must be exactly " + SNOMED_SYSTEM);
        }

        String code = coding.getCode();
        if (code == null || !code.matches("\\d+")) {
            throw new ValidationException("coding.code must contain digits only");
        }

        String display = coding.getDisplay();
        if (display == null || display.trim().isEmpty()) {
            throw new ValidationException("coding.display must not be blank");
        }
    }
}
