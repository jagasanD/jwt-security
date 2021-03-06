
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
public class UserBean {
    
    @ApiModelProperty(notes = "Id is required only updated time")
    private long id;
    
    @ApiModelProperty(notes = "userName is mandatory", required = true)
    @NotEmpty(message="UserName can not be Empty")
    private String userName;
    
    @ApiModelProperty(notes = "password is mandatory", required = true)
    @NotEmpty(message="UserName can not be Empty")
    private String password;
    
    @Min(value=15,message="Age must be greater then 15")
    private Integer age;
    
    @Email(message="Invalide Email ")
     @ApiModelProperty(notes = "Email is mandatory", required = true)
    private String email;
    
}
