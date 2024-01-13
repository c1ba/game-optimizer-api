package com.ciba.gameoptimizerapi.services.User;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.repositories.User.UserRepository;
import com.ciba.gameoptimizerapi.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JWTUtils jwtUtils;

//    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) throws BadRequestException {
        User data = userRepository.findByUsername(username).orElse(null);

        if (data == null) {
            throw new BadRequestException("User not found!");
        }

        if (!userRepository.login(data.getId(), password)) {
            throw new BadRequestException("Bad credentials!");
        }
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtils.generateJWTToken(data);
    }

}
