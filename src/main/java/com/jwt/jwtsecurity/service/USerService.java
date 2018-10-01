
package com.jwt.jwtsecurity.service;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.bean.LoginBean;
import com.jwt.jwtsecurity.bean.UserBean;

/**
 *
 * @author root
 */

public interface USerService {

    public Boolean save(UserBean bean)throws Exception;
    public GenericResponse loadUserByUsername(LoginBean bean)throws Exception;
    public GenericResponse fetchUsers()throws Exception;
    
}
