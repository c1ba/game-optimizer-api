package com.ciba.gameoptimizerapi.security;

import com.ciba.gameoptimizerapi.config.UserAuthenticationToken;
import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.repositories.User.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;


public class AuthProvider implements AuthenticationProvider {

    private static UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) {
        String username = auth.getPrincipal() + "";
        String password = auth.getCredentials() + "";

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new BadCredentialsException("User does not exist.");
        }

        if (!userRepository.login(user.get().getId(), password)) {
            throw new BadCredentialsException("Credentials must be wrong.");
        }

        return new UsernamePasswordAuthenticationToken(username, null);
    }

    @Override
    public boolean supports(Class<?> authType) {
        return authType.equals(UserAuthenticationToken.class);
    }
}
