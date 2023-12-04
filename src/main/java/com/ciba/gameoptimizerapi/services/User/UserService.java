package com.ciba.gameoptimizerapi.services.User;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;

public interface UserService {

    String login(String username, String password) throws BadRequestException;
}
