package com.ciba.gameoptimizerapi.services.Component;

import com.ciba.gameoptimizerapi.models.Component;

import java.util.List;
import java.util.UUID;

public interface ComponentService {

    List<Component> getAllComponents();

    List<Component> getComponentsByUUIDs(List<UUID> uuids);
}
