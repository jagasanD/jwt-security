
package com.jwt.jwtsecurity.bean;

import io.swagger.annotations.ApiModelProperty;
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
    private String userName;
    @ApiModelProperty(notes = "password is mandatory", required = true)
    private String password;
    
}
