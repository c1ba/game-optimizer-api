package com.ciba.gameoptimizerapi.services.ComponentCombos;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.requests.PostComponentRequest;

import java.util.List;

public interface ComponentCombosService {

    void create(List<Component> requestedComponents) throws BadRequestException;
}
