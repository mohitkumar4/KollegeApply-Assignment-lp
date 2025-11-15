package com.lp.landing_page.controller;


import com.lp.landing_page.model.Course;
import com.lp.landing_page.model.Lead;
import com.lp.landing_page.repository.CourseRepository;
import com.lp.landing_page.repository.LeadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final CourseRepository courseRepository;
    private final LeadRepository leadRepository;

    public ApiController(CourseRepository courseRepository, LeadRepository leadRepository) {
        this.courseRepository = courseRepository;
        this.leadRepository = leadRepository;
    }

    @GetMapping("/fees/{univCode}")
    public ResponseEntity<?> getFees(@PathVariable String univCode) {
        List<Course> courses = courseRepository.findByUniversityCode(univCode);
        HashMap<String, Object> resp = new HashMap<>();
        resp.put("university", univCode);
        resp.put("courses", courses);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/leads")
    public ResponseEntity<?> saveLead(@RequestBody Lead lead) {
        Lead saved = leadRepository.save(lead);
        return ResponseEntity.ok(saved);
    }
}
