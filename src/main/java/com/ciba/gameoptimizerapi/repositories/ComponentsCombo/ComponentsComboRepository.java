package com.ciba.gameoptimizerapi.repositories.ComponentsCombo;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;

import java.util.List;
import java.util.UUID;

public interface ComponentsComboRepository {

    List<ComponentsCombo> getComponentCombosByUUIDs(List<UUID> uuidList);
}