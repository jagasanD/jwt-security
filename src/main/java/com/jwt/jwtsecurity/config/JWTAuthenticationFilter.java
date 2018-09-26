/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.config;


/**
 *
 * @author root
 * 
 * Implement an authentication filter to issue JWTs to users sending credentials.
 */
/*@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  
    private AuthenticationManager authenticationManager;
    
      public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
  
      //where we parse the user's credentials and issue them to the AuthenticationManager.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Users user = new ObjectMapper().readValue(request.getInputStream(), Users.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getPassword(),
                    user.getUserName(),
                    new ArrayList<>()
            ));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
// these method called when a user successfully logs in. We use this method to generate a JWT for this user.
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication auth)
            throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        
        log.debug("--------------token------"+token);
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }

}
*/
 