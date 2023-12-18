package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.requests.LoginRequest;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.ciba.gameoptimizerapi.utils.JSONUtils.writeRequestBody;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    @Test
    void login_shouldSucceed() throws Exception {
        String username = "cramymozilla123",
        password = "user123";

        LoginRequest reqBody = new LoginRequest();
        reqBody.setUsername(username);
        reqBody.setPassword(password);

        String requestJson = writeRequestBody(reqBody);

        mvc.perform(post("/login").contentType("application/json").content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void login_shouldFail() throws Exception {
        String username = "cramymozilla123",
                password = "definetelynotawrongpassword";

        LoginRequest reqBody = new LoginRequest();
        reqBody.setUsername(username);
        reqBody.setPassword(password);

        String requestJson = writeRequestBody(reqBody);

        mvc.perform(post("/login").contentType("application/json").content(requestJson))
                .andExpect(status().isBadRequest());
    }
}
