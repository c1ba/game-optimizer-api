package com.ciba.gameoptimizerapi.services.Component;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository repository;

    @Override
    public List<Component> getAllComponents() {
        return repository.getAllComponents();
    }

    @Override
    public List<Component> getComponentsByUUIDs(List<UUID> uuids) {
        return repository.getComponentsByUUIDs(uuids);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteByUUID(uuid);
    }
}
