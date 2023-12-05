package com.ciba.gameoptimizerapi.unit.services;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import com.ciba.gameoptimizerapi.services.Component.ComponentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ComponentServiceTest {

    @InjectMocks
    private ComponentServiceImpl service;

    @Mock
    private ComponentRepository repository;

    private Component processor;
    private Component graphics;
    private Component ram;

    private List<Component> allComponents;

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

        allComponents = List.of(processor, graphics, ram);
    }

    @Test
    void getALlComponents_shouldSucceed() {
        given(repository.getAllComponents()).willReturn(allComponents);

        assertFalse(service.getAllComponents().isEmpty());
    }
}
