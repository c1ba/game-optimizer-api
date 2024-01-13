package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.models.*;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import org.jooq.DSLContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PerformanceFilesTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    private User user;
    private Component processor;
    private Component graphics;
    private Component ram;
    private Game game;

    @BeforeEach
    void init() {
        user = dsl.selectFrom(USERS)
                .where(USERS.USERNAME.eq("cramymozilla123"))
                .fetchAnyInto(User.class);

        processor = dsl.selectFrom(COMPONENTS)
                .where(COMPONENTS.TYPE.eq(ComponentType.processor))
                .fetchAnyInto(Component.class);
        System.out.println(processor.getName());
        graphics = dsl.selectFrom(COMPONENTS)
                .where(COMPONENTS.TYPE.eq(ComponentType.graphics_card))
                .fetchAnyInto(Component.class);
        System.out.println(graphics.getName());
        ram = dsl.selectFrom(COMPONENTS)
                .where(COMPONENTS.TYPE.eq(ComponentType.ram))
                .fetchAnyInto(Component.class);
        System.out.println(ram.getCapacity());
        game = dsl.selectFrom(GAMES)
                .fetchAnyInto(Game.class);
        System.out.println(game.getName());
    }

    @Test
    void getPerformanceFiles_asGuest_shouldSucceed() throws Exception {

        mvc.perform(get("/performance_files/"+ game.getId()
                        + "?processorId=" + processor.getId()
                        + "&graphicsId=" + graphics.getId()
                        + "&ramId=" + ram.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getPerformanceFiles_asUser_shouldSucceed() throws Exception {
        mvc.perform(get("/performance_files/"+ game.getId()
                        + "?processorId=" + processor.getId()
                        + "&graphicsId=" + graphics.getId()
                        + "&ramId=" + ram.getId()).with(user(user)))
                .andExpect(status().isOk());
    }

    @Test
    void getPerformanceFiles_shouldThrowNotFound() throws Exception {
        mvc.perform(get("/games/"+ game.getId()
                        + "?processorId=" + UUID.randomUUID()
                        + "&graphicsId=" + graphics.getId()
                        + "&ramId=" + ram.getId()))
                .andExpect(status().isNotFound());
    }
}
