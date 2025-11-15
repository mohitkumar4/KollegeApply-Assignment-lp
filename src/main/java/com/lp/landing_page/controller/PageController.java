package com.lp.landing_page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/pri-univ-A"})
    public String univA(Model model) {
        model.addAttribute("universityCode", "pri-univ-A");
        model.addAttribute("universityName", "Alpha Private University");
        return "univ";
    }

    @GetMapping("/pri-univ-B")
    public String univB(Model model) {
        model.addAttribute("universityCode", "pri-univ-B");
        model.addAttribute("universityName", "Beta Private Institute");
        return "univ";
    }
}
