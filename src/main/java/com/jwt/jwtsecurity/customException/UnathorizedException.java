
package com.jwt.jwtsecurity.customException;

/**
 *
 * @author root
 */
public class UnathorizedException extends RuntimeException{
    
   public  UnathorizedException(String msg){
    super(msg);
    }
    
}
