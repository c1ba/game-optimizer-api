package com.ciba.gameoptimizerapi.config;


import com.ciba.gameoptimizerapi.security.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

    // It dictates the HTTP system how it should be configured for security.
    // Ignore the fact that some things used here are deprecated, the update came 3 months ago from writing this code
    // and I don't have any source for copying the new way yet.

    private final JWTAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        try {
            http.cors().configurationSource(corsConfigurationSource);
            http.csrf().disable()
                    .authorizeHttpRequests(auth ->

                        auth.requestMatchers("/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/games").permitAll()
                                .requestMatchers(HttpMethod.GET, "/components").permitAll()
                                .requestMatchers(HttpMethod.POST, "/performance_files").permitAll()
                                .anyRequest().authenticated()
                    );
            http
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
        catch (Exception ex) {
            throw new IllegalStateException("Not able to build security filter chain", ex);
        }
    }

}
