/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author root
 */
@Controller
@ApiIgnore
public class SwaggerController {

    @GetMapping("/")
    public String getSwaggerApi() {
         return "redirect:/swagger-ui.html";
    }
}
