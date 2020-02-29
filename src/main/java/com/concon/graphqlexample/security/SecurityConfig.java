package com.concon.graphqlexample.security;

import com.concon.graphqlexample.auth.ApplicationUserService;
import com.concon.graphqlexample.jwt.JwtConfig;
import com.concon.graphqlexample.jwt.JwtTokenVerifier;
import com.concon.graphqlexample.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.crypto.SecretKey;

@CrossOrigin(origins = "*",maxAge = 3600)
@EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)// bu bize metodlar düzeyinde kullanabileceğimiz yeni bir güvenlik kısıtlamaları dünyası açtı
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public SecurityConfig(          ApplicationUserService applicationUserService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig) {
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/graphql").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/graphiql").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
              //  .inMemoryAuthentication()
               // .passwordEncoder(passwordEncoder())
//                .withUser("mi3o").password("{noop}52").roles("USER").and()
//                .withUser("admin").password("{noop}nbusr123").roles("USER", "ADMIN");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}