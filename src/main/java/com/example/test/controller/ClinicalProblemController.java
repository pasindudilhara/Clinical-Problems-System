package com.example.test.controller;

import com.example.test.modal.ClinicalProblem;
import com.example.test.service.ClinicalProblemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients/{patientId}/problems")
public class ClinicalProblemController {

    private final ClinicalProblemService clinicalProblemService;

    public ClinicalProblemController(ClinicalProblemService clinicalProblemService) {
        this.clinicalProblemService = clinicalProblemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicalProblem create(@PathVariable Long patientId,
                                  @RequestBody ClinicalProblem problem) {
        return clinicalProblemService.createForPatient(patientId, problem);
    }

    @GetMapping
    public List<ClinicalProblem> findAll(@PathVariable Long patientId) {
        return clinicalProblemService.findByPatientId(patientId);
    }

    @PutMapping("/{problemId}")
    public ClinicalProblem update(@PathVariable Long patientId,
                                  @PathVariable Long problemId,
                                  @RequestBody ClinicalProblem problem) {
        return clinicalProblemService.update(patientId, problemId, problem);
    }

    @DeleteMapping("/{problemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long patientId,
                       @PathVariable Long problemId) {
        clinicalProblemService.delete(patientId, problemId);
    }
}
