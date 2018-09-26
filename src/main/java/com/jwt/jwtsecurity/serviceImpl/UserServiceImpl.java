/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.serviceImpl;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.bean.UserBean;
import com.jwt.jwtsecurity.model.UserAuth;
import com.jwt.jwtsecurity.model.Users;
import com.jwt.jwtsecurity.repository.UserAuthRepository;
import com.jwt.jwtsecurity.repository.UserRepository;
import com.jwt.jwtsecurity.service.USerService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.jwt.jwtsecurity.config.JwtGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author root
 */
@Service
@Slf4j
public class UserServiceImpl implements USerService {
    // public class UserServiceImpl  implements USerService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtGenerator jwtGenerator;
    @Autowired
    UserAuthRepository authRepository;

    @Override
    public Boolean save(UserBean bean) {
        Users user = new Users();
        user.setUserName(bean.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(bean.getPassword()));
        userRepository.save(user);
        return true;

    }

    @Override
    public GenericResponse loadUserByUsername(UserBean bean) {
        Users user = userRepository.findByUserName(bean.getUserName());
        GenericResponse response = null;
        log.debug("----------------user----------"+user.getUserName());
        
       // Assert.isNull(user, "Unathorized Access");
      //  Assert.isTrue(!(bCryptPasswordEncoder.matches(bean.getPassword(), user.getPassword())), "Unathorized Access");
      /*  if (!(bCryptPasswordEncoder.matches(bean.getPassword(), user.getPassword()))) {
            Assert.isNull(user, "Unathorized Access");
        }*/
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        UserAuth userAuth = new UserAuth();
        userAuth.setUser(user);
        // userAuth.setJwttokenexpire(jwtUtilService.getTokenexpireTime());
        userAuth.setUuid(randomUUIDString);
        response = new GenericResponse(user, randomUUIDString, userAuth.getId());
        userAuth.setToken(jwtGenerator.generateToken(response));
        authRepository.save(userAuth);
        return new GenericResponse(user, userAuth.getToken(), userAuth.getId(), null);
    }

    @Override
    public GenericResponse fetchUsers() {
       return new GenericResponse(userRepository.findAll());
    }

}
