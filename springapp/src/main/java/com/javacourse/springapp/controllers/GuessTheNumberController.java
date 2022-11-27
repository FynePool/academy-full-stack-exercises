package com.javacourse.springapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.javacourse.springapp.model.IndovinaForm;
import com.javacourse.springapp.model.Tentativo;

@Controller
@RequestMapping("/guessthenumber")
@SessionAttributes({ "secret", "tentativi" })
public class GuessTheNumberController {

    @GetMapping
    public String form(IndovinaForm indovina) {
        return "guessthenumber";
    }

    @PostMapping
    public String submit(@ModelAttribute("indovinaForm") @Valid IndovinaForm indovina,
            BindingResult errors,
            @ModelAttribute("secret") Integer secret,
            @ModelAttribute("tentativi") List<Tentativo> tentativi) {
        if (errors.hasErrors()) {
            return "guessthenumber";
        }
        tentativi.add(new Tentativo(indovina.getNumber(), secret));
        return "redirect:/guessthenumber";
    }

    @GetMapping("/reset")
    public String reset(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/guessthenumber";
    }

    @ModelAttribute("secret")
    public Integer secret() {
        return ((int) (Math.random() * 101)) + 100;
    }

    @ModelAttribute("tentativi")
    public List<Tentativo> tentativi() {
        return new ArrayList<>();
    }

}