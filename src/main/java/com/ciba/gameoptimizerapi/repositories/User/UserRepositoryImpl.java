package com.ciba.gameoptimizerapi.repositories.User;

import com.ciba.gameoptimizerapi.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.USERS;
@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private DSLContext dsl;

    @Override
    public boolean login(UUID userId, String password) {
        Optional<String> actualPassword = dsl.select(USERS.PASSWORD)
                .from(USERS)
                .where(USERS.ID.eq(userId))
                .fetchOptionalInto(String.class);

        return actualPassword.isPresent() && actualPassword.get().equals(password);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return dsl.select(USERS.ID, USERS.ROLE, USERS.USERNAME, USERS.PASSWORD).from(USERS)
                .where(USERS.USERNAME.eq(username))
                .fetchOptionalInto(User.class);
    }

    @Override
    public List<User> findByUUIDs(List<UUID> userUUIDs) {
        return dsl.selectFrom(USERS)
                .where(USERS.ID.in(userUUIDs))
                .fetchInto(User.class);
    }
}
