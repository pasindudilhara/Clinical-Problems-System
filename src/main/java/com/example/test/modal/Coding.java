package com.example.test.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Coding {

    @Column(name = "coding_system", nullable = false)
    private String system;

    @Column(name = "coding_code", nullable = false)
    private String code;

    @Column(name = "coding_display", nullable = false)
    private String display;

    public Coding() {
    }

    public Coding(String system, String code, String display) {
        this.system = system;
        this.code = code;
        this.display = display;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}