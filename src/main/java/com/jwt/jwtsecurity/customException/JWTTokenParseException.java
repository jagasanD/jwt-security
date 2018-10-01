
package com.jwt.jwtsecurity.customException;

/**
 *
 * @author root
 */
public class JWTTokenParseException extends RuntimeException{
    
    public JWTTokenParseException(String msg){
        super(msg);
    }
}
