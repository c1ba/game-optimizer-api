package com.ciba.gameoptimizerapi.services.ComponentCombos;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.requests.PostComponentRequest;

import java.util.List;
import java.util.UUID;

public interface ComponentCombosService {

    void create(List<Component> requestedComponents) throws BadRequestException;

    void delete(UUID uuid);

    List<ComponentsCombo> getComponentCombosByUUIDs(List<UUID> uuids);
}
