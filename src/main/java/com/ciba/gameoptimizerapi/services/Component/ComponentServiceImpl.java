package com.ciba.gameoptimizerapi.services.Component;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository repository;

    @Override
    public List<Component> getAllComponents() {
        return repository.getAllComponents();
    }
}
