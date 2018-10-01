/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.config;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.bean.UserBean;
import com.jwt.jwtsecurity.model.UserAuth;
import com.jwt.jwtsecurity.model.Users;
import com.jwt.jwtsecurity.repository.UserAuthRepository;
import com.jwt.jwtsecurity.repository.UserRepository;
import static java.util.Collections.emptyList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 * @author root
 */
@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
  public class UserDetailsServiceImpl{
    
   UserRepository  userRepository;
    @Autowired
   JwtGenerator jwtGenerator;
   @Autowired
   UserAuthRepository authRepository;
  /*   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users applicationUser = userRepository.findByUserName(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUserName(), applicationUser.getPassword(), emptyList());
    }*/
    
     public GenericResponse loadUserByUsername(UserBean bean)throws Exception {
        Users user = userRepository.findByUserName(bean.getUserName());
        GenericResponse response =null;
        if (user == null) {
           Assert.isNull(user, "Unathorized Access");
        }
        
         UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        UserAuth userAuth = new UserAuth();
        userAuth.setUser(user);
       // userAuth.setJwttokenexpire(jwtUtilService.getTokenexpireTime());
        userAuth.setUuid(randomUUIDString);
        response = new GenericResponse(user,randomUUIDString,userAuth.getId());
        userAuth.setToken(jwtGenerator.generateToken(response));
         authRepository.save(userAuth);
        return new GenericResponse(user, userAuth.getToken(), userAuth.getId(),null);
    }
}
