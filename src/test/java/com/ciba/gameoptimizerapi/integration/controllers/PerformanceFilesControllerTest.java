package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.models.*;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.*;
import static com.ciba.gameoptimizerapi.utils.JSONUtils.writeRequestBody;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PerformanceFilesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    private User user;
//  private UserDetails admin;
    private Component processor;
    private Component graphics;
    private Component ram;
    private ComponentsCombo combo;
    private Game game;

    @BeforeEach
    void init() {
        user = dsl.selectFrom(USERS)
                .where(USERS.USERNAME.eq("cramymozilla123"))
                .fetchAnyInto(User.class);
//        admin = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("excelsiorfox123")).fetchAnyInto(User.class);
        processor = dsl.selectFrom(COMPONENTS)
                .where(COMPONENTS.TYPE.eq(ComponentType.processor))
                .fetchAnyInto(Component.class);
        graphics = dsl.selectFrom(COMPONENTS)
                .where(COMPONENTS.TYPE.eq(ComponentType.graphics_card))
                .fetchAnyInto(Component.class);
        ram = dsl.selectFrom(COMPONENTS)
                .where(COMPONENTS.TYPE.eq(ComponentType.ram))
                .fetchAnyInto(Component.class);
        game = dsl.selectFrom(GAMES)
                .fetchAnyInto(Game.class);

        combo = dsl.insertInto(COMPONENT_COMBOS,
                        COMPONENT_COMBOS.PROCESSOR_ID,
                        COMPONENT_COMBOS.GRAPHICS_CARD_ID,
                        COMPONENT_COMBOS.RAM_ID)
                .values(processor.getId(),
                        graphics.getId(),
                        ram.getId())
                .returning(COMPONENT_COMBOS.ID,
                        COMPONENT_COMBOS.PROCESSOR_ID,
                        COMPONENT_COMBOS.GRAPHICS_CARD_ID,
                        COMPONENT_COMBOS.RAM_ID
                )
                .fetchInto(ComponentsCombo.class).get(0);

    }

    @AfterEach
    void fin() {
        dsl.deleteFrom(COMPONENT_COMBOS)
                .where(COMPONENT_COMBOS.ID.eq(combo.getId()))
                .execute();
    }

    @Test
    void getPerformanceFiles_asGuest_shouldSucceed() throws Exception {

        mvc.perform(get("/games/"+ game.getId()
                        + "?processorId=" + processor.getId()
                        + "&graphicsId=" + graphics.getId()
                        + "&ramId=" + ram.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getPerformanceFiles_asUser_shouldSucceed() throws Exception {
        mvc.perform(get("/games/"+ game.getId()
                        + "?processorId=" + processor.getId()
                        + "&graphicsId=" + graphics.getId()
                        + "&ramId=" + ram.getId()).with(user(user)))
                .andExpect(status().isOk());
    }
}
