package dev.rozhkova.ibank.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyAppUserDetailsService myAppUserDetailsService;
    private final AppAuthenticationEntryPoint appAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/bank/info", "/users/create").permitAll()
                .antMatchers("/users/*/bankAccount/list", "/users/*/bankAccount/*",
                        "/users/*/bankAccount/*/bankCard/list", "/users/*/paymentHistory/list", "/users/*/paymentHistory",
                        "/users/*/payments/create").hasAnyRole("ADMIN","USER")
                .antMatchers("/bankAccount/create", "/bankAccount/list", "/bankAccount/list", "/bankAccount/remove/*", "/bankCard/list",
                        "/users/list").hasRole("ADMIN")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().httpBasic().realmName("MY APP REALM")
                .authenticationEntryPoint(appAuthenticationEntryPoint);
    }
}