package com.ciba.gameoptimizerapi.repositories.ComponentsCombo;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComponentsComboRepository {

    List<ComponentsCombo> getComponentCombosByUUIDs(List<UUID> uuidList);
    Optional<ComponentsCombo> getComponentsComboByComponents(UUID processorUUID, UUID graphicsCardUUID, UUID ramUUID);

    void create(ComponentsCombo data);

    void deleteByUUID(UUID uuid);
}
