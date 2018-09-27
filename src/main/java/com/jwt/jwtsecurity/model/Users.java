
package com.jwt.jwtsecurity.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Id is required only updated time")
    private long id;
    @ApiModelProperty(notes = "userName is mandatory", required = true)
    private String userName;
     @ApiModelProperty(notes = "password is mandatory", required = true)
    private String password;
}
