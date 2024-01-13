package com.ciba.gameoptimizerapi.config;


import com.ciba.gameoptimizerapi.security.AuthProvider;
import com.ciba.gameoptimizerapi.security.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

    // It dictates the HTTP system how it should be configured for security.
    // Ignore the fact that some things used here are deprecated, the update came 3 months ago from writing this code
    // and I don't have any source for copying the new way yet.

    private final JWTAuthFilter jwtAuthFilter;
    private final AuthProvider authProvider;

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        {
                            Set<String> whitelist;

                            whitelist = new HashSet<>(Set.of("/login", "/performance_files/**", "/games", "/components", "/swagger-ui.html"));

                            auth.requestMatchers(whitelist.toArray(new String[0])).permitAll()
                                    .requestMatchers(HttpMethod.GET, "/**").permitAll()
                                    .requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ROLE_ADMIN")
                                    .anyRequest().authenticated();

                            auth.shouldFilterAllDispatcherTypes(false);

//                            auth.anyRequest().permitAll();
                        }
                    );
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));

        http
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
