package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.requests.PostComponentRequest;
import com.ciba.gameoptimizerapi.requests.PostComponentsComboRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jooq.DSLContext;
import org.jooq.meta.derby.sys.Sys;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.*;
import static com.ciba.gameoptimizerapi.utils.JSONUtils.writeRequestBody;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComponentCombosTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    private UserDetails user;
    private UserDetails admin;
    private final String CLEAR_CREATED = "clear_created";
    private final String SELECT_FOR_DELETION = "select_for_deletion";

    private UUID createdComboId;
    private final String processorName = "Powerful Processor 9000";

    @BeforeEach
    void init(TestInfo info) {
        user = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("cramymozilla123")).fetchAnyInto(User.class);
        admin = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("excelsiorfox123")).fetchAnyInto(User.class);

        if (info.getTags().contains(SELECT_FOR_DELETION)) {
            UUID processorUUID = dsl.select(COMPONENTS.ID).from(COMPONENTS)
                    .where(COMPONENTS.NAME.eq(processorName))
                    .fetchOneInto(UUID.class);

            createdComboId = dsl.select(COMPONENT_COMBOS.ID).from(COMPONENT_COMBOS)
                    .where(COMPONENT_COMBOS.PROCESSOR_ID.eq(processorUUID))
                    .fetchOneInto(UUID.class);
        }
    }

    @Test
    @Order(1)
    void createComponentsCombo_asGuest_shouldBeForbidden() throws Exception {
        String jsonRequest = generatePostRequest();

        mvc.perform(post("/component_combos").contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isForbidden());
    }
    @Test
    @Order(2)
    void createComponentsCombo_asUser_shouldSucceed() throws Exception {
        String jsonRequest = generatePostRequest();

        mvc.perform(post("/component_combos").with(user(user)).contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    void deleteComponentCombo_asUser_shouldBeForbidden() throws Exception {
        mvc.perform(delete("/component_combos/" + createdComboId).with(user(user)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Tag(SELECT_FOR_DELETION)
    @Order(4)
    void deleteComponentCombo_asAdmin_shouldSucceed() throws Exception {
        mvc.perform(delete("/component_combos/" + createdComboId).with(user(admin)))
                .andExpect(status().isOk());
    }

    private String generatePostRequest() throws JsonProcessingException {
        PostComponentsComboRequest request = new PostComponentsComboRequest();
        PostComponentRequest processorReq = new PostComponentRequest();
        processorReq.setName(processorName);
        processorReq.setCapacity(3.0f);

        PostComponentRequest graphicsReq = new PostComponentRequest();
        graphicsReq.setName("Powerful Graphics Card GTX 9050");
        graphicsReq.setCapacity(16.0f);

        PostComponentRequest ramReq = new PostComponentRequest();
        ramReq.setCapacity(99.0f);

        request.setProcessor(processorReq);
        request.setGraphicsCard(graphicsReq);
        request.setRam(ramReq);

        return writeRequestBody(request);
    }
}
