package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.COMPONENTS;
import static com.ciba.gameoptimizerapi.models.jooq.Tables.USERS;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ComponentsTest {

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
    void getAllComponents_asGuest_shouldSucceed() throws Exception {
        mvc.perform(get("/components")).andExpect(status().isOk());
    }

    @Test
    void getAllComponents_asUser_shouldSucceed() throws Exception {
        mvc.perform(get("/components").with(user(user))).andExpect(status().isOk());
    }

    @Test
    void deleteComponent_asGuest_shouldBeForbidden() throws Exception {
        mvc.perform(delete("/components/" + UUID.randomUUID())).andExpect(status().isForbidden());
    }

    @Test
    void getAllComponents_asUser_shouldBeFobidden() throws Exception {
        mvc.perform(delete("/components/" + UUID.randomUUID()).with(user(user))).andExpect(status().isForbidden());
    }

    @Test
    void getAllComponents_asAdmin_shouldSucceed() throws Exception {
        UUID processorUUID = dsl.select(COMPONENTS.ID).from(COMPONENTS)
                .where(COMPONENTS.NAME.like("%Powerful%"))
                .and(COMPONENTS.TYPE.eq(ComponentType.processor))
                .fetchAnyInto(UUID.class);
        mvc.perform(delete("/components/" + processorUUID).with(user(admin))).andExpect(status().isOk());

        UUID graphicsUUID = dsl.select(COMPONENTS.ID).from(COMPONENTS)
                .where(COMPONENTS.NAME.like("%Powerful%"))
                .and(COMPONENTS.TYPE.eq(ComponentType.graphics_card))
                .fetchAnyInto(UUID.class);
        mvc.perform(delete("/components/" + graphicsUUID).with(user(admin))).andExpect(status().isOk());

        UUID ramUUID = dsl.select(COMPONENTS.ID).from(COMPONENTS)
                .where(COMPONENTS.CAPACITY.eq(99.0f))
                .and(COMPONENTS.TYPE.eq(ComponentType.ram))
                .fetchAnyInto(UUID.class);
        mvc.perform(delete("/components/" + ramUUID).with(user(admin))).andExpect(status().isOk());
    }
}
