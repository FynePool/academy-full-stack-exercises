package com.javacourse.springapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacourse.springapp.model.Tentativo;

@RestController
@RequestMapping("/api/v1")
public class RemainingTriesController {
    
    @GetMapping("/tentativi")
    public List<Tentativo> tentativi() {
        return List.of(new Tentativo(150, 115),
            new Tentativo(130, 115),
            new Tentativo(110, 115),
            new Tentativo(115, 115));
    }
}