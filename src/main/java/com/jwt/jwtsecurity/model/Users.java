
package com.jwt.jwtsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;





/**
 *
 * @author root
 */
@Entity
@Getter
@Setter
public class Users {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private Integer age;
    private String email;
}
