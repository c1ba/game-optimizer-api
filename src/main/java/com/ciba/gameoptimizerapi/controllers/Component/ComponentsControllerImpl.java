package com.ciba.gameoptimizerapi.controllers.Component;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.responses.ComponentResponse;
import com.ciba.gameoptimizerapi.responses.SortedComponentsResponse;
import com.ciba.gameoptimizerapi.services.Component.ComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ComponentsControllerImpl implements ComponentsController {

    private final ComponentService service;

    @Override
    public ResponseEntity<SortedComponentsResponse> getAllComponents() {
        List<Component> result = service.getAllComponents();
        List<ComponentResponse> responses = result.stream().map(ComponentResponse::fromComponent).toList();

        return ResponseEntity.ok().body(SortedComponentsResponse.sortComponentResponses(responses));
    }
}
