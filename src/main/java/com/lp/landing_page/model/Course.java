package com.lp.landing_page.model;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String universityCode; // "univA" or "univB"
    private String code; // e.g., BTECH
    private String name;
    private Integer minFee;
    private Integer maxFee;
    private String breakdownJson;

    public Course() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUniversityCode() { return universityCode; }
    public void setUniversityCode(String universityCode) { this.universityCode = universityCode; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getMinFee() { return minFee; }
    public void setMinFee(Integer minFee) { this.minFee = minFee; }
    public Integer getMaxFee() { return maxFee; }
    public void setMaxFee(Integer maxFee) { this.maxFee = maxFee; }
    public String getBreakdownJson() { return breakdownJson; }
    public void setBreakdownJson(String breakdownJson) { this.breakdownJson = breakdownJson; }
}