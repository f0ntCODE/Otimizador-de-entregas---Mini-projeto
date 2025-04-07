package com.project.container.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.container.functions.Mochila;

@Controller
public class mainController {

    //************************************Redirecionamento de páginas

    @GetMapping("/")
    public String mainPage() {
        
        
        return "index";
    }

    @GetMapping("/metodos")
    public String metodosPage() {

        return "basic_methods";
    }

    @GetMapping("/info")
    public String infoPage() {

        return "info";
    }


    
    
    
}
