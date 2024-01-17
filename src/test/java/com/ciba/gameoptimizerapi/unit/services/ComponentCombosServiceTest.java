package com.ciba.gameoptimizerapi.unit.services;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepository;
import com.ciba.gameoptimizerapi.services.ComponentCombos.ComponentCombosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ComponentCombosServiceTest {

    @InjectMocks
    private ComponentCombosServiceImpl service;

//    @Mock
//    private ComponentRepository componentRepository;

    @Mock
    private ComponentsComboRepository repository;

    private Component processor;
    private Component graphics;
    private Component ram;
    private ComponentsCombo combo;

//    private List<Component> allComponents;

    @BeforeEach
    void init() {
        processor = Component.builder()
                .id(UUID.fromString("34b8d0f5-90af-439f-9054-b6d982c27662"))
                .name("Intel Core i5-6600")
                .type(ComponentType.processor)
                .capacity(3.3F)
                .build();

        graphics = Component.builder()
                .id(UUID.fromString("53606477-fa18-425f-8f25-cf630584f1da"))
                .name("NVIDIA GeForce GTX 960")
                .type(ComponentType.graphics_card)
                .capacity(4.0F)
                .build();

        ram = Component.builder()
                .id(UUID.fromString("e6966a01-4c13-4786-a7d7-b96535b809ef"))
                .type(ComponentType.ram)
                .capacity(6.0F)
                .build();

        combo = ComponentsCombo.builder().id(UUID.randomUUID())
                .ramId(ram.getId())
                .graphcisCardId(graphics.getId())
                .processorId(processor.getId())
                .build();

//        allComponents = List.of(processor, graphics, ram);
    }

    @Test
    void delete_shouldSucceed() {
        service.delete(combo.getId());
        verify(repository).deleteByUUID(combo.getId());
    }
}
