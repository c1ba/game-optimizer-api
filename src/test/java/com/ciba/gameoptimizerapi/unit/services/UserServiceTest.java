package com.ciba.gameoptimizerapi.unit.services;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.models.jooq.enums.UserType;
import com.ciba.gameoptimizerapi.repositories.User.UserRepository;
import com.ciba.gameoptimizerapi.security.JWTUtils;
import com.ciba.gameoptimizerapi.services.User.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Mock
    private JWTUtils jwtUtils;

    private User user;

    @BeforeEach
    void init() {
        user = User.builder()
                .id(UUID.randomUUID())
                .username("tester1")
                .password("definetelyasecurepassword")
                .role(UserType.user)
                .build();
    }

    @Test
    void login_shouldSucceed() throws BadRequestException {
        given(repository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
        given(repository.login(user.getId(), user.getPassword())).willReturn(true);
        given(jwtUtils.generateJWTToken(user)).willReturn("randomavailabletoken");

        userService.login(user.getUsername(), user.getPassword());
    }

    @Test
    void login_onInexistentUser_shouldThrowError() {
        given(repository.findByUsername(user.getUsername())).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.login(user.getUsername(), user.getPassword())).isInstanceOf(BadRequestException.class);
    }

    @Test
    void login_onWrongPassword_shouldThrowError() {
        given(repository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
        given(repository.login(user.getId(), user.getPassword())).willReturn(false);

        assertThatThrownBy(() -> userService.login(user.getUsername(), user.getPassword())).isInstanceOf(BadRequestException.class);
    }
}
