
package com.jwt.jwtsecurity.customException;

/**
 *
 * @author root
 */
public class JWTHeaderMissingException extends RuntimeException{
    
    
    public JWTHeaderMissingException(String msg){
         super(msg);
    }
    
}
