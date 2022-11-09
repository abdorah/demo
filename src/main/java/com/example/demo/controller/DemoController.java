package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.DemoDTO;
import com.example.demo.service.DemoService;


@Controller
@RequestMapping("/demo")
public class DemoController {
    
    @Autowired
    private DemoService demoService;

    @PreAuthorize("hasAuthority('typical authority')")
    @GetMapping(value="/id={id}")
    public String getDemoById(@PathVariable(name = "id") String id, Model model) {
        DemoDTO demo;
        try {
            demo = demoService.getDemoById(UUID.fromString(id));
            model.addAttribute("content", demo.getMarkdownContent());
            return "demo";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
    
}
