package com.ciba.gameoptimizerapi.repositories.Component;

import com.ciba.gameoptimizerapi.models.Component;

import java.util.List;
import java.util.UUID;

public interface ComponentRepository {

    List<Component> getComponentsByUUIDs(List<UUID> idList);

    List<Component> getAllComponents();

    void create(Component data);

    void deleteByUUID(UUID uuid);

    List<Component> getComponentsByNamesAndCapacities(List<Component> data);
}
