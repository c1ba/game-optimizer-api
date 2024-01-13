package com.ciba.gameoptimizerapi.controllers.Component;

import com.ciba.gameoptimizerapi.exceptions.NotFoundException;
import com.ciba.gameoptimizerapi.exceptions.UnauthorizedException;
import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.responses.ComponentResponse;
import com.ciba.gameoptimizerapi.responses.SortedComponentsResponse;
import com.ciba.gameoptimizerapi.services.Component.ComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @Override
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails, UUID uuid) throws NotFoundException, UnauthorizedException {
//        if (userDetails == null || !userDetails.getAuthorities().contains("ROLE_ADMIN")) {
//            throw new UnauthorizedException("Unauthorized.");
//        }
        if (service.getComponentsByUUIDs(List.of(uuid)).isEmpty()) {
            throw new NotFoundException("Component with uuid " + uuid + " not found.");
        }

        service.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
