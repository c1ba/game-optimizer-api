package com.ciba.gameoptimizerapi.controllers.PerformanceFiles;

import com.ciba.gameoptimizerapi.exceptions.NotFoundException;
import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.PerformanceFiles;
import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;
import com.ciba.gameoptimizerapi.responses.PerformanceFilesResponse;
import com.ciba.gameoptimizerapi.services.Component.ComponentService;
import com.ciba.gameoptimizerapi.services.PerformanceFile.PerformanceFileService;
import lombok.RequiredArgsConstructor;
import org.jooq.tools.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.utils.VerificationUtils.getInexistingUUIDsFromRequested;

@RestController
@RequiredArgsConstructor
public class PerformanceFilesControllerImpl implements PerformanceFilesController {

    private final PerformanceFileService service;
    private final ComponentService componentService;
    @Override
    public ResponseEntity<List<PerformanceFilesResponse>> getPerformanceFiles(UserDetails userDetails,
                                                                              UUID gameUUID,
                                                                              UUID processorUUID,
                                                                              UUID graphicsUUID,
                                                                              UUID ramUUID) throws NotFoundException {
        List<UUID> requestedUUIDs = List.of(processorUUID, graphicsUUID, ramUUID);
        List<UUID> notFoundUUIDs = getInexistingUUIDsFromRequested(
                componentService.getComponentsByUUIDs(requestedUUIDs).stream().map(Component::getId).toList(),
                requestedUUIDs
        );
        if (!notFoundUUIDs.isEmpty()) {
            throw new NotFoundException("No components found with the below ids:\n" +
                    StringUtils.join(notFoundUUIDs, ";\n"));
        }

        PerformanceFilesRequest request = new PerformanceFilesRequest();
        request.setGameId(gameUUID);
        request.setProcessorId(processorUUID);
        request.setGraphicsCardId(graphicsUUID);
        request.setRamId(ramUUID);
        List<PerformanceFiles> result = service.getPerformanceFiles(request);

        List<PerformanceFilesResponse> response = new ArrayList<>();
        for (PerformanceFiles config : result) {
            response.add(PerformanceFilesResponse.fromPerformanceFile(config, userDetails != null));
        }

        return ResponseEntity.ok().body(response);
    }
}
