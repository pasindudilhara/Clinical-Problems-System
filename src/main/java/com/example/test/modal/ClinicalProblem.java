package com.example.test.modal;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clinical_problems")
public class ClinicalProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Coding coding;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public ClinicalProblem() {
    }

    public ClinicalProblem(Long id, Coding coding) {
        this.id = id;
        this.coding = coding;
    }

    public Long getId() {
        return id;
    }

    public Coding getCoding() {
        return coding;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCoding(Coding coding) {
        this.coding = coding;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClinicalProblem)) return false;
        ClinicalProblem that = (ClinicalProblem) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
