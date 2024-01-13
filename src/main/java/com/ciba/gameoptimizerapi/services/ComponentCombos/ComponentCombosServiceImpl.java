package com.ciba.gameoptimizerapi.services.ComponentCombos;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
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
                    .filter(component -> List.of(ComponentType.processor, ComponentType.graphics_card)
                            .contains(component.getType()) ?
                            component.getName().equals(requestedComponent.getName())
                                    && component.getCapacity() == requestedComponent.getCapacity() :
                            component.getCapacity() == requestedComponent.getCapacity())
                    .findFirst();

            if (exists.isEmpty()) {
                requestedComponent.setId(UUID.randomUUID());
                componentRepository.create(requestedComponent);
            }
            else {
                requestedComponent.setId(exists.get().getId());
            }

            switch (requestedComponent.getType().getLiteral()) {
                case "processor" -> processorUUID = requestedComponent.getId();
                case "graphics_card" -> graphicsUUID = requestedComponent.getId();
                case "ram" -> ramUUID = requestedComponent.getId();
            }
        }

        ComponentsCombo data = ComponentsCombo.builder()
                .id(UUID.randomUUID())
                .processorId(processorUUID)
                .graphcisCardId(graphicsUUID)
                .ramId(ramUUID)
                .build();

        if (repository.getComponentsComboByComponents(processorUUID, graphicsUUID, ramUUID).isPresent()) {
            throw new BadRequestException("Combo already exists!");
        }
        repository.create(data);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteByUUID(uuid);
    }

    @Override
    public List<ComponentsCombo> getComponentCombosByUUIDs(List<UUID> uuids) {
        return repository.getComponentCombosByUUIDs(uuids);
    }
}
