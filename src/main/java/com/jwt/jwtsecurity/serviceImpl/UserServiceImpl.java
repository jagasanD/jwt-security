
package com.jwt.jwtsecurity.serviceImpl;

import com.jwt.jwtsecurity.bean.GenericResponse;
import com.jwt.jwtsecurity.bean.LoginBean;
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
import com.jwt.jwtsecurity.config.JwtGenerator;
import com.jwt.jwtsecurity.customException.UnathorizedException;
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
    public Boolean save(UserBean bean)throws Exception {
        Users user = new Users();
        user.setUserName(bean.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(bean.getPassword()));
        userRepository.save(user);
        return true;

    }

    @Override
    public GenericResponse loadUserByUsername(LoginBean bean) throws Exception {
        Users user = userRepository.findByUserName(bean.getUserName());
        GenericResponse response = null;
        log.debug("----------------user----------"+user.getUserName());
        
       // Assert.isNull(user, "Unathorized Access");
      //  Assert.isTrue(!(bCryptPasswordEncoder.matches(bean.getPassword(), user.getPassword())), "Password is in correct ");
       if (!(bCryptPasswordEncoder.matches(bean.getPassword(), user.getPassword()))) {
            throw new UnathorizedException("Unauthorized exception!");
        }
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
    public GenericResponse fetchUsers()throws Exception {
       return new GenericResponse(userRepository.findAll());
    }

}
