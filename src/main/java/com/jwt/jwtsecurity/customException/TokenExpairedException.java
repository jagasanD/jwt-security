
package com.jwt.jwtsecurity.customException;

/**
 *
 * @author root
 */
public class TokenExpairedException  extends RuntimeException{
    
    public TokenExpairedException(String msg){
    super(msg);
    
    }
    
}
