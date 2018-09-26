/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/**
 *
 * @author root
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //  @Autowired
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.out.println("---------------Ignoring URLS------------------");
       // web.ignoring().antMatchers("/**");
       // web.ignoring().antMatchers("/","/v2/api-docs", "/swagger-resources", "/configuration/security", "/swagger-ui.html");
        web.ignoring().antMatchers( "/","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");

    }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
   // http.authorizeRequests().antMatchers("/**").permitAll().and().csrf().disable();
    /*   http.csrf().disable().authorizeRequests().antMatchers("/api/user/**")
               .authenticated()
               .and()
//                //call the Custom Filter two verify the token etc...
             .addFilter(new JWTAuthorizationFilter(authenticationManager())).exceptionHandling().accessDeniedHandler(getaccessDeniedHandler());
  */
    
    
    
    http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		
		http.csrf().disable().authorizeRequests()
		.antMatchers("/api/user/**")
		.hasAnyAuthority("USER")
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().accessDeniedHandler(getaccessDeniedHandler())
		.and()
		.addFilter( new JWTAuthorizationFilter(authenticationManager()));
    
    
}

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
   private AccessDeniedHandler getaccessDeniedHandler(){
       
      return  new CustomAccessDeniedHandler();
     /* return new AccessDeniedHandler() {
           @Override
           public void handle(HttpServletRequest hsr, HttpServletResponse hsr1, AccessDeniedException ade) throws IOException, ServletException {
               ade.printStackTrace();
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       };*/
    
    }
}
