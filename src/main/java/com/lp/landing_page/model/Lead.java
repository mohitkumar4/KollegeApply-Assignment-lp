package com.lp.landing_page.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leads")
public class Lead {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String universityCode;
    private String name;
    private String email;
    private String phone;
    private String state;
    private String course;
    private String intake;

    private Boolean consent;

    public Lead() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUniversityCode() { return universityCode; }
    public void setUniversityCode(String universityCode) { this.universityCode = universityCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getIntake() { return intake; }
    public void setIntake(String intake) { this.intake = intake; }
    public boolean isConsent() { return consent; }
    public void setConsent(boolean consent) { this.consent = consent; }
}
