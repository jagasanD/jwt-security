
package com.jwt.jwtsecurity.bean;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author root
 */
@Getter
@Setter
public class LoginBean {
   
    @ApiModelProperty(notes = "userName is mandatory", required = true)
    @NotEmpty(message="UserName can not be Empty")
    private String userName;
    
    @ApiModelProperty(notes = "password is mandatory", required = true)
    @NotEmpty(message="UserName can not be Empty")
    private String password;
    
  
    
}
