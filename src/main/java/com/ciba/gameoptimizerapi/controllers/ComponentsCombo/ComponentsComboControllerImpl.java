package com.ciba.gameoptimizerapi.controllers.ComponentsCombo;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.exceptions.NotFoundException;
import com.ciba.gameoptimizerapi.exceptions.UnauthorizedException;
import com.ciba.gameoptimizerapi.requests.PostComponentsComboRequest;
import com.ciba.gameoptimizerapi.services.ComponentCombos.ComponentCombosService;
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
public class ComponentsComboControllerImpl implements ComponentsComboController {

    private final ComponentCombosService service;

    @Override
    public ResponseEntity<Void> postComponentCombo(@AuthenticationPrincipal UserDetails userDetails, PostComponentsComboRequest request) throws BadRequestException, UnauthorizedException {
        service.create(PostComponentsComboRequest.extractComponentsData(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails, UUID uuid) throws NotFoundException, UnauthorizedException {
        if (service.getComponentCombosByUUIDs(List.of(uuid)).isEmpty()) {
            throw new NotFoundException("Component with uuid " + uuid + " not found.");
        }

        service.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
