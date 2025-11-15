package com.lp.landing_page.repository;


import com.lp.landing_page.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> { }
