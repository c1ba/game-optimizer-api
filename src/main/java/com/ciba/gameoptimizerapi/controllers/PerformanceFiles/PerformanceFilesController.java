package com.ciba.gameoptimizerapi.controllers.PerformanceFiles;

import com.ciba.gameoptimizerapi.models.jooq.tables.PerformanceFiles;
import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/performance_files")
public interface PerformanceFilesController {

    @GetMapping
    @Operation(summary = "Get All Components", description = "Gets all components.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
    })
    List<PerformanceFiles> getPerformanceFiles(@RequestBody PerformanceFilesRequest request);
}
