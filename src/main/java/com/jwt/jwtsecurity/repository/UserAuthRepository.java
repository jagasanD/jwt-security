/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.repository;

import com.jwt.jwtsecurity.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author root
 */
public interface UserAuthRepository extends JpaRepository<UserAuth, Long>{
    
    
    
    
}
