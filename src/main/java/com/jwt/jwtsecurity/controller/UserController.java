package com.jwt.jwtsecurity.controller;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.bean.UserBean;
import com.jwt.jwtsecurity.service.USerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(value = "User information", description = "User Controller performance operation User")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    USerService uSerService;
    
 

    @PostMapping("/sign-up")
    @ApiOperation(value = "save the user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully saved user")
        ,
        @ApiResponse(code = 401, message = "You are not authorized to view the resource")
        ,
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
        ,
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Boolean save(@Valid @RequestBody UserBean bean)throws Exception {
        return uSerService.save(bean);
    }

 

    @GetMapping("/get-data")
    @ApiOperation(value = "fetch user list ", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully fetch user list ")
        ,
        @ApiResponse(code = 401, message = "You are not authorized to view the resource")
        ,
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
        ,
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public GenericResponse fetchUsers()throws Exception {
        return uSerService.fetchUsers();
    }

}
