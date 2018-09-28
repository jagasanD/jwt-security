package com.jwt.jwtsecurity.config;

import static com.jwt.jwtsecurity.config.SecurityConstants.HEADER_STRING;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author root Implement an authorization filter to validate requests
 * containing JWTs.
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtGenerator jwtGenerator;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        // we can allow to access with secretkey not required token 

        if (header == null) {
            throw new RuntimeException("----XAuth Required-------");
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
// Parsing the token and verify the token and return new UsernamePasswordAuthenticationToken

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            Claims claim = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token).getBody();
            /*  String authId = claim.get("authId").toString();
            String randomString = claim.get("randomString").toString();
            String id = claim.get("id").toString();
            String role = claim.get("role").toString();
            String userName = claim.getSubject();*/
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("USER"));

            /*  *************************Access the API based on seckretKey ****************** */
 /* 
        String clientIdOrSecretkey = req.getHeader("secretKey");
        find in the database secretKey is avlilable or not if not throws exception else
        set the role and seckret key in UsernamePasswordAuthenticationToken() 
        like role is ACCESSAPI 
        **************************************************************************************
             */
            if (claim != null) {
                return new UsernamePasswordAuthenticationToken(token, null, roles);
            } else {
                throw new RuntimeException("JWT clain parsed Exception");
            }
        } else {
            throw new RuntimeException("JWT Token header missing ");
        }
    }

}
