
package com.jwt.jwtsecurity.service;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.bean.UserBean;

/**
 *
 * @author root
 */

public interface USerService {

    public Boolean save(UserBean bean);
    public GenericResponse loadUserByUsername(UserBean bean);
    public GenericResponse fetchUsers();
    
}
