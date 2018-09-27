/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.controller;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.bean.UserBean;
import com.jwt.jwtsecurity.service.USerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author root
 */
@RestController
@CrossOrigin
@Api(value = "Public Controller", description = "No need XAuth for access these controller method or URL")
@RequestMapping("/public")
public class PublicController {

    @Autowired
    USerService uSerService;

    @PostMapping("/login")
    @ApiOperation(value = "Login operation ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Loggedin ")
        ,
        @ApiResponse(code = 401, message = "You are not authorized to view the resource")
        ,
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
        ,
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public GenericResponse login(@RequestBody UserBean bean) {
        return uSerService.loadUserByUsername(bean);
    }
}
