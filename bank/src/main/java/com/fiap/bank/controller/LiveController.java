package com.fiap.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class LiveController {


    @GetMapping
    private String health() {
        return "bank api - Natasha Lopes";
    }
}
