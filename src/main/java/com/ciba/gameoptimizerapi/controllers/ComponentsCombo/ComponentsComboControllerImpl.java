package com.ciba.gameoptimizerapi.controllers.ComponentsCombo;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.requests.PostComponentsComboRequest;
import com.ciba.gameoptimizerapi.services.ComponentCombos.ComponentCombosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComponentsComboControllerImpl implements ComponentsComboController {

    private final ComponentCombosService service;

    @Override
    public ResponseEntity<Void> postComponentCombo(UserDetails userDetails, PostComponentsComboRequest request) throws BadRequestException {
        service.create(PostComponentsComboRequest.extractComponentsData(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
