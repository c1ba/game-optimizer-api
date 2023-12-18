package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.models.User;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.USERS;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ComponentsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    private UserDetails user;
//    private UserDetails admin;

    @BeforeEach
    void init() {
        user = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("cramymozilla123")).fetchAnyInto(User.class);
//        admin = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("excelsiorfox123")).fetchAnyInto(User.class);
    }

    @Test
    void getAllComponents_asGuest_shouldSucceed() throws Exception {
        mvc.perform(get("/components")).andExpect(status().isOk());
    }

    @Test
    void getAllComponents_asUser_shouldSucceed() throws Exception {
        mvc.perform(get("/components").with(user(user))).andExpect(status().isOk());
    }
}
