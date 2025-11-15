package com.lp.landing_page.repository;


import com.lp.landing_page.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUniversityCode(String universityCode);
    Course findByUniversityCodeAndCode(String universityCode, String code);
}
