/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author root
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class GenericResponse {
    
    private Object payLoad;
    private String status;
    private String erMessage;
    private String statusCode;
    private String randomString;
    private Long uuid;
    private String token;
    
    public GenericResponse() {

    }

    public GenericResponse(Object payLoad) {

        this.payLoad = payLoad;
    }
     public GenericResponse(Object payLoad,String randomString,Long uuid) {
        this.payLoad = payLoad;
        this.randomString= randomString;
        this.uuid= uuid;
    }

    public GenericResponse(Object payLoad, String statusCode) {
        this.payLoad = payLoad;
        this.statusCode = statusCode;
    }

    public GenericResponse(String erMessage, String statusCode) {
        this.erMessage = erMessage;
        this.statusCode = statusCode;
    }
    
      public GenericResponse(Object payLoad,String token,Long uuid,String status) {
        this.payLoad = payLoad;
        this.token= token;
        this.uuid= uuid;
    }

    
}
