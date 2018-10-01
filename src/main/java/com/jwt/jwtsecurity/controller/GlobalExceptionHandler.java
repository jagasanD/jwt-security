package com.jwt.jwtsecurity.controller;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.customException.JWTHeaderMissingException;
import com.jwt.jwtsecurity.customException.JWTTokenParseException;
import com.jwt.jwtsecurity.customException.TokenExpairedException;
import com.jwt.jwtsecurity.customException.UnathorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author root
 */

@RestControllerAdvice
@CrossOrigin
@Slf4j
public class GlobalExceptionHandler  {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public GenericResponse IllegalArgumentExceptionHandler(IllegalArgumentException ex) {
      //  ex.printStackTrace();
        log.debug("------------------IllegalArgumentException-----------------");
        return new GenericResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());

    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(JWTHeaderMissingException.class)
    public GenericResponse jWTHeaderMissingExceptionHandler(JWTHeaderMissingException ex) {
    //    ex.printStackTrace();
    log.debug("------------------JwtHeaderMissing -----------------");
        return new GenericResponse(ex.getMessage(), HttpStatus.CONFLICT.toString());

    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(JWTTokenParseException.class)
    public GenericResponse jWTTokenParseExceptionHandler(JWTTokenParseException ex) {
      //  ex.printStackTrace();
         log.debug("------------------invalide tocken -----------------");
        return new GenericResponse(ex.getMessage(), HttpStatus.CONFLICT.toString());

    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(TokenExpairedException.class)
    public GenericResponse tokenExpairedExceptionHandler(TokenExpairedException ex) {
        ex.printStackTrace();
        return new GenericResponse(ex.getMessage(), HttpStatus.CONFLICT.toString());

    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(UnathorizedException.class)
    public GenericResponse unathorizedExceptionHandler(UnathorizedException ex) {
      //  ex.printStackTrace();
         log.debug("------------------unAthorized user -----------------");
        return new GenericResponse(ex.getMessage(), HttpStatus.CONFLICT.toString());

    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(Exception.class)
    public GenericResponse exceptionHandler(Exception ex) {
        ex.printStackTrace();
            log.debug("------------------other issue -----------------");
        return new GenericResponse(ex.getMessage(), HttpStatus.CONFLICT.toString());

    }

}
