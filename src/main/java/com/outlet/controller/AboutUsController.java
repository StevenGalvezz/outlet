/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.outlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author xsf
 */
@Controller
@RequestMapping("/aboutUs")
public class AboutUsController {
    @GetMapping("/sobre-nosotros")
    public String sobreNosotros() {
        return "aboutUs/sobre-nosotros";
    }
}
