package com.ciba.gameoptimizerapi.controllers.Game;

import com.ciba.gameoptimizerapi.responses.GameResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequestMapping("/games")
public interface GameController {

    @GetMapping
    @Operation(summary = "Get All Games", description = "Gets all games.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
    })
    ResponseEntity<List<GameResponse>> getAllGames();
}
