package br.com.alura.forum.controller;

import br.com.alura.forum.repository.OpenTopicsByCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reports")
public class ReportsController {

    @Autowired
    private OpenTopicsByCategoryRepository openTopicsByCategoryRepository;

    @GetMapping("/open-topics-by-category")
    public String showOpenTopicsByCategoryReport() {
        return "report";
    }
}
