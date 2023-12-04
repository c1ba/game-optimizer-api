package com.ciba.gameoptimizerapi.services.Game;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.Game;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepository;
import com.ciba.gameoptimizerapi.repositories.Game.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository repository;
    private final ComponentsComboRepository componentsComboRepository;
    private final ComponentRepository componentRepository;

    // Get all the games, all the associated component combos and their associated components.
    // For each game, find the data for the minimum components, and set them to the game.
    @Override
    public List<Game> getGames() {
        return repository.getGames(null, null, null);
    }

    @Override
    public List<Game> getGames(String name, Integer releaseYear, ComponentsCombo componentsCombo) {
        return repository.getGames(name, releaseYear, componentsCombo);
    }
}
