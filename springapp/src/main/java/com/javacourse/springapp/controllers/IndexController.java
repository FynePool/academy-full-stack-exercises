package com.javacourse.springapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(@RequestParam(name = "name", defaultValue = "Home") String name, Model model) {
        log.info("Called method index of IndexController.\nShould change view to index.html");
        /* using instead param "HttpServletRequest req"
        String name = req.getParameter("name");
        if (name==null || name.isBlank()){
            name = "Thymeleaf";
        }*/
        model.addAttribute("lastpage", name); 

        return "index";
    }

    @GetMapping("/bookstore")
    public String bookstore(Model model) {
        log.info("Called method bookstore of IndexController.\nShould change view to bookstore.html");
        model.addAttribute("msg", "Thymeleaf");
        return "bookstore";
    }

    @GetMapping("/form")
    public String form(Model model) {
        log.info("Called method form of IndexController.\nShould change view to form.html");
        model.addAttribute("msg", "Thymeleaf");
        return "form";
    }

    @GetMapping("/liste")
    public String liste(Model model) {
        log.info("Called method liste of IndexController.\nShould change view to liste.html");
        model.addAttribute("msg", "Thymeleaf");
        return "liste";
    }

    @GetMapping("/books")
    public String books(Model model) {
        List<String> books = List.of("Harry Potter", "Dieci Piccoli Indiani", "Design Patterns");
        model.addAttribute("books", books);
        return "books";
    }
}