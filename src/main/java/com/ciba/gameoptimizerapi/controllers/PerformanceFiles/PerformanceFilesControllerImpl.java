package com.ciba.gameoptimizerapi.controllers.PerformanceFiles;

import com.ciba.gameoptimizerapi.models.PerformanceFiles;
import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;
import com.ciba.gameoptimizerapi.responses.PerformanceFilesResponse;
import com.ciba.gameoptimizerapi.services.PerformanceFile.PerformanceFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PerformanceFilesControllerImpl implements PerformanceFilesController {

    private final PerformanceFileService service;
    @Override
    public ResponseEntity<List<PerformanceFilesResponse>> getPerformanceFiles(UserDetails userDetails,
                                                                              UUID gameUUID,
                                                                              UUID processorUUID,
                                                                              UUID graphicsUUID,
                                                                              UUID ramUUID) {
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
