
package com.laudate.dominum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, active FROM users WHERE username=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM roles WHERE username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/contact/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/checkout/**").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/cart-checkout/**").permitAll()
                        .requestMatchers("/payment/**").permitAll()
                        .requestMatchers("/save-customer/**").permitAll()
                        .requestMatchers("/access-denied/**").permitAll()
                        .requestMatchers("/send-success/**").permitAll()
                        .requestMatchers("/order-success/**").permitAll()
                        .requestMatchers("/admin/dashboard/").hasRole("ADMIN").anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser").permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer -> configurer
                                .accessDeniedPage("/access-denied"));
        return http.build();
    }

}


