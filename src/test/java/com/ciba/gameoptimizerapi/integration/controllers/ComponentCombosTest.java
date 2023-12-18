package com.ciba.gameoptimizerapi.integration.controllers;

import com.ciba.gameoptimizerapi.models.User;
import com.ciba.gameoptimizerapi.requests.PostComponentRequest;
import com.ciba.gameoptimizerapi.requests.PostComponentsComboRequest;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.*;
import static com.ciba.gameoptimizerapi.utils.JSONUtils.writeRequestBody;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ComponentCombosTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    private UserDetails user;
//    private UserDetails admin;
    private final String CLEAR_CREATED = "clear_created";

    @BeforeEach
    void init() {
        user = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("cramymozilla123")).fetchAnyInto(User.class);
//        admin = dsl.selectFrom(USERS).where(USERS.USERNAME.eq("excelsiorfox123")).fetchAnyInto(User.class);
    }

    @AfterEach
    void fin(TestInfo info) {
        if (info.getTags().contains(CLEAR_CREATED)) {
            UUID processorUUID = dsl.selectFrom(COMPONENTS)
                            .where(COMPONENTS.NAME.eq("Powerful Processor 9000"))
                                    .fetchAnyInto(UUID.class);

            dsl.deleteFrom(COMPONENTS)
                    .where(COMPONENTS.NAME.in(List.of("Powerful Processor 9000", "Powerful Graphics Card GTX 9050")))
                    .or(COMPONENTS.CAPACITY.eq(99.0f))
                    .execute();

            dsl.deleteFrom(COMPONENT_COMBOS)
                    .where(COMPONENT_COMBOS.PROCESSOR_ID.eq(processorUUID))
                    .execute();
        }
    }

    @Test
    @Tag(CLEAR_CREATED)
    void postComponentsCombo_shouldSucceed() throws Exception {
        PostComponentsComboRequest request = new PostComponentsComboRequest();
        PostComponentRequest processorReq = new PostComponentRequest();
        processorReq.setName("Powerful Processor 9000");
        processorReq.setCapacity(3.0f);

        PostComponentRequest graphicsReq = new PostComponentRequest();
        graphicsReq.setName("Powerful Graphics Card GTX 9050");
        graphicsReq.setCapacity(16.0f);

        PostComponentRequest ramReq = new PostComponentRequest();
        ramReq.setCapacity(99.0f);

        request.setProcessor(processorReq);
        request.setGraphicsCard(graphicsReq);
        request.setRam(ramReq);

        String jsonRequest = writeRequestBody(request);

        mvc.perform(post("/component_combos").contentType("application/json").content(jsonRequest))
                .andExpect(status().isCreated());
    }
}
