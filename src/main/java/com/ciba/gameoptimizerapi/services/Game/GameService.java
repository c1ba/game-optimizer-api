package com.ciba.gameoptimizerapi.services.Game;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.Game;

import java.util.List;

public interface GameService {

    List<Game> getGames(String name, Integer releaseYear, ComponentsCombo componentsCombo);

    List<Game> getGames();

}
