package com.ciba.gameoptimizerapi.unit.services;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.Game;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepositoryImpl;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepository;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepositoryImpl;
import com.ciba.gameoptimizerapi.repositories.Game.GameRepository;
import com.ciba.gameoptimizerapi.repositories.Game.GameRepositoryImpl;
import com.ciba.gameoptimizerapi.services.Game.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameServiceImpl service;

    @Mock
    private GameRepository repository;

    @Mock
    private ComponentsComboRepository componentsComboRepository;

    @Mock
    private ComponentRepository componentRepository;

    private Component processor1, processor2;
    private Component graphics1, graphics2;
    private Component ram1, ram2;
    private ComponentsCombo combo1, combo2;
    private Game game1, game2;

    @BeforeEach
    void init() {
        processor1 = Component.builder()
                .id(UUID.randomUUID())
                .name("Intel Core i5-6600")
                .type(ComponentType.processor)
                .capacity(3.3F)
                .build();

        processor2 = Component.builder()
                .id(UUID.randomUUID())
                .name("Intel Core i5-2300")
                .type(ComponentType.processor)
                .capacity(2.8F)
                .build();

        graphics1 = Component.builder()
                .id(UUID.randomUUID())
                .name("NVIDIA GeForce GTX 960")
                .type(ComponentType.graphics_card)
                .capacity(4.0F)
                .build();

        graphics2 = Component.builder()
                .id(UUID.randomUUID())
                .name("Nvidia GeForce GTX 780")
                .type(ComponentType.graphics_card)
                .capacity(2.8F)
                .build();

        ram1 = Component.builder()
                .id(UUID.randomUUID())
                .type(ComponentType.ram)
                .capacity(3.3F)
                .build();
        ram2 = Component.builder()
                .id(UUID.randomUUID())
                .type(ComponentType.ram)
                .capacity(8.0F)
                .build();

        combo1 = ComponentsCombo.builder()
                .id(UUID.randomUUID())
                .processorId(processor1.getId())
                .graphcisCardId(graphics1.getId())
                .ramId(ram1.getId())
                .build();

        combo2 = ComponentsCombo.builder()
                .id(UUID.randomUUID())
                .processorId(processor2.getId())
                .graphcisCardId(graphics2.getId())
                .ramId(ram2.getId())
                .build();

        game1 = Game.builder()
                .id(UUID.randomUUID())
                .name("Hogwarts Legacy")
                .releaseYear(2023)
                .minimumComponentsId(combo1.getId())
                .build();

        game2 = Game.builder()
                .id(UUID.randomUUID())
                .name("Detroit: Become Human")
                .releaseYear(2020)
                .minimumComponentsId(combo2.getId())
                .build();
    }

    @Test
    void getGames_shouldRetrieveAll() {
        given(repository.getGames(null, null, null)).willReturn(List.of(game1, game2));

        assertTrue(Arrays.deepEquals(service.getGames().toArray(), List.of(game1, game2).toArray()));
    }

    @Test
    void getGames_shouldRetrieveByName() {
        given(repository.getGames("Hog", null, null)).willReturn(List.of(game1));

        assertTrue(Arrays.deepEquals(service.getGames("Hog", null, null).toArray(), List.of(game1).toArray()));
    }

    @Test
    void getGames_shouldRetrieveByReleaseYear() {
        given(repository.getGames(null, 2023, null)).willReturn(List.of(game1));

        assertTrue(Arrays.deepEquals(service.getGames(null, 2023, null).toArray(), List.of(game1).toArray()));
    }

    @Test
    void getGames_shouldRetrieveByMinimumComponents() {
        given(repository.getGames(null, null, combo1)).willReturn(List.of(game1));

        assertTrue(Arrays.deepEquals(service.getGames(null, null, combo1).toArray(), List.of(game1).toArray()));
    }
}
