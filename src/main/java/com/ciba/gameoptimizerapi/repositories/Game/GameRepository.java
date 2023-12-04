package com.ciba.gameoptimizerapi.repositories.Game;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.Game;

import java.util.List;

public interface GameRepository {

    List<Game> getGames(String name, Integer releaseYear, ComponentsCombo componentsCombo);
}
