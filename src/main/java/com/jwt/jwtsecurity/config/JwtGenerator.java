/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.config;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author root
 */
@Component
 @Slf4j
public class JwtGenerator {
        
    public String generateToken(GenericResponse response){
    Users user = (Users)response.getPayLoad();
    Claims claims = Jwts.claims();
        claims.setSubject(user.getUserName());
      //  claims.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME));
        claims.put("id", String.valueOf(user.getId()));
        claims.put("randomString", String.valueOf(response.getRandomString()));
        claims.put("authId", String.valueOf(response.getUuid()));
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET).compact();
    
    }
}
