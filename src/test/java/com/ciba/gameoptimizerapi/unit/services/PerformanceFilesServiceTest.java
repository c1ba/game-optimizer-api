package com.ciba.gameoptimizerapi.unit.services;

import com.ciba.gameoptimizerapi.models.*;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.ciba.gameoptimizerapi.models.jooq.enums.UserType;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepository;
import com.ciba.gameoptimizerapi.repositories.PerformanceFile.PerformanceFileRepository;
import com.ciba.gameoptimizerapi.repositories.User.UserRepository;
import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;
import com.ciba.gameoptimizerapi.services.PerformanceFile.PerformanceFileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PerformanceFilesServiceTest {

    @InjectMocks
    private PerformanceFileServiceImpl service;

    @Mock
    private PerformanceFileRepository repository;

    @Mock
    private ComponentsComboRepository componentsComboRepository;

    @Mock
    private UserRepository userRepository;

    private Component processor;
    private Component graphics;
    private Component ram;
    private ComponentsCombo combo;
    private Game game;
    private PerformanceFiles files;
    private User user;

    @BeforeEach
    void init() {
        processor = Component.builder()
                .id(UUID.randomUUID())
                .name("Intel Core i5-6600")
                .type(ComponentType.processor)
                .capacity(3.3F)
                .build();

        graphics = Component.builder()
                .id(UUID.randomUUID())
                .name("NVIDIA GeForce GTX 960")
                .type(ComponentType.graphics_card)
                .capacity(4.0F)
                .build();

        ram = Component.builder()
                .id(UUID.randomUUID())
                .type(ComponentType.ram)
                .capacity(6.0F)
                .build();

        combo = ComponentsCombo.builder()
                .id(UUID.randomUUID())
                .processorId(processor.getId())
                .graphcisCardId(graphics.getId())
                .ramId(ram.getId())
                .build();

        game = Game.builder()
                .id(UUID.randomUUID())
                .name("Hogwarts Legacy")
                .yearRelease(2023)
                .minimumComponentsId(combo.getId())
                .build();

        user = User.builder()
                .id(UUID.randomUUID())
                .username("tester1")
                .password("definetelyasecurepassword")
                .role(UserType.user)
                .build();

        files = PerformanceFiles.builder()
                .id(UUID.randomUUID())
                .componentsComboId(combo.getId())
                .userId(user.getId())
                .performanceFilesNames(new String[] {"directx_enhancer.dll", "definetely_not_a_virus.dll"})
                .build();
    }

    @Test
    void getPerformanceFiles_shouldSucceed() {
        PerformanceFilesRequest req = new PerformanceFilesRequest();
        req.setProcessorId(processor.getId());
        req.setGraphicsCardId(graphics.getId());
        req.setRamId(ram.getId());
        req.setGameId(game.getId());

        given(componentsComboRepository.getComponentsComboByComponents(
                req.getProcessorId(), req.getGraphicsCardId(), req.getRamId())).willReturn(Optional.of(combo));
        given(repository.getPerformanceFilesNamesByComponentsComboAndGame
                (combo.getId(), req.getGameId())).willReturn(List.of(files));
        given((userRepository.findByUUIDs(List.of(user.getId())))).willReturn(List.of(user));

        List<PerformanceFiles> result = service.getPerformanceFiles(req);

        assertFalse(result.isEmpty());
    }

    @Test
    void getPerformanceFiles_shouldReturnEmpty() {
        PerformanceFilesRequest req = new PerformanceFilesRequest();
        req.setProcessorId(processor.getId());
        req.setGraphicsCardId(graphics.getId());
        req.setRamId(ram.getId());
        req.setGameId(game.getId());

        given(componentsComboRepository.getComponentsComboByComponents(
                req.getProcessorId(), req.getGraphicsCardId(), req.getRamId())).willReturn(Optional.empty());

        List<PerformanceFiles> result = service.getPerformanceFiles(req);

        assertTrue(result.isEmpty());
    }
}
