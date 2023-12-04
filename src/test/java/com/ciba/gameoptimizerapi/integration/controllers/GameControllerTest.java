package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.models.jooq.enums.UserType;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.USERS;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    private UserDetails user;
    private UserDetails admin;

    @BeforeEach
    void init() {
        user = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("cramymozilla123")).fetchAnyInto(User.class);
        admin = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("excelsiorfox123")).fetchAnyInto(User.class);
    }

    @Test
    void getGames_asUser_shouldSucceed() throws Exception {
        mvc.perform(get("/games").with(user(user))).andExpect(status().isOk());
    }

    @Test
    void getGames_asAdmin_shouldSucceed() throws Exception {
        mvc.perform(get("/games").with(user(admin))).andExpect(status().isOk());
    }
}
