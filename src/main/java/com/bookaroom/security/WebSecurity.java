package com.bookaroom.security;

import static com.bookaroom.security.SecurityConstants.SIGN_UP_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bookaroom.services.impl.UserPasswordEncryptionService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter
{
    private UserDetailsService userDetailsService;
    private UserPasswordEncryptionService userPasswordEncryptionService;

    public WebSecurity(
        UserDetailsService userDetailsService,
        UserPasswordEncryptionService userPasswordEncryptionService)
    {
        this.userDetailsService = userDetailsService;
        this.userPasswordEncryptionService = userPasswordEncryptionService;
    }

    @Override
    protected void configure(HttpSecurity http)
        throws Exception
    {
        http.cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, SIGN_UP_URL)
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)
        throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncryptionService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
