package com.example.forohub.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class pruebaController {

    @GetMapping
    public String prueba(){
        return "hola mundo prueba";
    }












}


