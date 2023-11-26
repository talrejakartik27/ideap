package com.asml.ideap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .anyRequest().fullyAuthenticated()
        .and()
      .formLogin();

    return http.build();
  }

//    @Autowired
//    UserDetailsServiceProvider userDetailsService;

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .ldapAuthentication()
        .userDnPatterns("uid={0},ou=people")
        .groupSearchBase("ou=groups")
        .contextSource()
          .url("ldap://localhost:8389/dc=springframework,dc=org")
          .and()
        .passwordCompare()
          .passwordEncoder(new BCryptPasswordEncoder())
          .passwordAttribute("userPassword");
    //auth.userDetailsService(null);
  }

//   @Bean
//   public PasswordEncoder passwordEncoder(){
//     return NoOpPasswordEncoder.getInstance();
//   }
  

}

//@Configuration
//public class WebSecurityConfig {

//  @Bean
//  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//    return http
//      .authorizeRequests()
//      .anyRequest().authenticated()
//      .and()
//      .formLogin(Customizer.withDefaults())
//      .build();
//  }
//}