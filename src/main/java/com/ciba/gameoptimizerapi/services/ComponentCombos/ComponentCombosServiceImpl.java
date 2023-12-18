package com.ciba.gameoptimizerapi.services.ComponentCombos;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComponentCombosServiceImpl implements ComponentCombosService {

    private final ComponentsComboRepository repository;
    private final ComponentRepository componentRepository;

    @Override
    public void create(List<Component> requestedComponents) throws BadRequestException {
        List<Component> existing = componentRepository.getComponentsByNamesAndCapacities(requestedComponents);

        UUID processorUUID = null;
        UUID graphicsUUID = null;
        UUID ramUUID = null;

        // Verify if component exists. If not, create and add for continuing the process.
        for (Component requestedComponent : requestedComponents) {
            Optional<Component> exists = existing.stream()
                    .filter(component -> component.getName().equals(requestedComponent.getName())
                    && component.getCapacity() == requestedComponent.getCapacity())
                    .findFirst();

            if (exists.isEmpty()) {
                requestedComponent.setId(UUID.randomUUID());
                componentRepository.create(requestedComponent);
            }

            switch (requestedComponent.getType().getLiteral()) {
                case "PROCESSOR" -> processorUUID = requestedComponent.getId();
                case "GRAPHICS_CARD" -> graphicsUUID = requestedComponent.getId();
                case "RAM" -> ramUUID = requestedComponent.getId();
            }
        }

        ComponentsCombo data = ComponentsCombo.builder()
                .id(UUID.randomUUID())
                .processorId(processorUUID)
                .graphcisCardId(graphicsUUID)
                .ramId(ramUUID)
                .build();

        if (repository.getComponentsComboByComponents(processorUUID, graphicsUUID, ramUUID).isEmpty()) {
            throw new BadRequestException("Combo already exists!");
        }
        repository.create(data);
    }
}
