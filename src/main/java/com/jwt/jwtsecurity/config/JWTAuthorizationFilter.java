package com.jwt.jwtsecurity.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwt.jwtsecurity.bean.GenericResponse;
import static com.jwt.jwtsecurity.config.SecurityConstants.HEADER_STRING;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.PrintWriter;
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
        String token = req.getHeader(HEADER_STRING);
        try {
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
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(token, null, roles);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(req, res);
                } else {
                    generateResponseError("INVALID_TOKEN", "invalide token", res);

                }
            } else {
                generateResponseError("HEADER_MISSING", "couldn't find the header", res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            generateResponseError("FILTER_CONFLICT", "Something went wrong", res);
        }
    }

    public void generateResponseError(String status, String message, HttpServletResponse res) throws JsonProcessingException, IOException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        GenericResponse resp = new GenericResponse(status, message);
        String jsonRespString = ow.writeValueAsString(resp);
        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        writer.write(jsonRespString);

    }

}
