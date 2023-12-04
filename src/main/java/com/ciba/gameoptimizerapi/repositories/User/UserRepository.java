package com.ciba.gameoptimizerapi.repositories.User;

import com.ciba.gameoptimizerapi.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    boolean login(UUID userId, String password);

    Optional<User> findByUsername(String username);

    List<User> findByUUIDs(List<UUID> userUUIDs);
}
