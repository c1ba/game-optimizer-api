package com.ciba.gameoptimizerapi.controllers.Game;

import com.ciba.gameoptimizerapi.models.Game;
import com.ciba.gameoptimizerapi.responses.GameResponse;
import com.ciba.gameoptimizerapi.services.Game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameControllerImpl implements GameController {

    private final GameService service;

    @Override
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<Game> result = service.getGames();
        List<GameResponse> response = new ArrayList<>();

        for (Game game : result) {
            response.add(GameResponse.fromGame(game));
        }
        return ResponseEntity.ok().body(response);
    }
}
