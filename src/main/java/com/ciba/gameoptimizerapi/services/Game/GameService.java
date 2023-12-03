package com.ciba.gameoptimizerapi.services.Game;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.Game;

import java.util.List;

public interface GameService {

    List<Game> getGames(String name, int releaseYear, ComponentsCombo componentsCombo);

    List<Game> getGames();

}
