package com.lp.landing_page;


import com.lp.landing_page.model.Course;
import com.lp.landing_page.repository.CourseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final CourseRepository courseRepository;

    public DataLoader(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void load() {
        if (courseRepository.count() > 0) return;

        Course c1 = new Course();
        c1.setUniversityCode("pri-univ-A");
        c1.setCode("BTECH");
        c1.setName("B.Tech Computer Science");
        c1.setMinFee(80000);
        c1.setMaxFee(200000);
        c1.setBreakdownJson("{\"tuition_per_year\":50000, \"hostel_per_year\":15000}");

        Course c2 = new Course();
        c2.setUniversityCode("pri-univ-A");
        c2.setCode("MBA");
        c2.setName("Master of Business Administration");
        c2.setMinFee(100000);
        c2.setMaxFee(300000);
        c2.setBreakdownJson("{\"tuition_per_year\":80000, \"library\":5000}");

        Course c3 = new Course();
        c3.setUniversityCode("pri-univ-B");
        c3.setCode("BSC");
        c3.setName("B.Sc Mathematics");
        c3.setMinFee(40000);
        c3.setMaxFee(120000);
        c3.setBreakdownJson("{\"tuition_per_year\":30000, \"lab_charges\":5000}");

        courseRepository.save(c1);
        courseRepository.save(c2);
        courseRepository.save(c3);
    }
}
