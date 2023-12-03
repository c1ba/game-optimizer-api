package com.ciba.gameoptimizerapi.services.Game;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.Game;
import com.ciba.gameoptimizerapi.repositories.Component.ComponentRepository;
import com.ciba.gameoptimizerapi.repositories.ComponentsCombo.ComponentsComboRepository;
import com.ciba.gameoptimizerapi.repositories.Game.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        List<Game> result = repository.getGames(null, null, null);
        List<ComponentsCombo> requiredCombos = componentsComboRepository.getComponentCombosByUUIDs(
                result.stream().map(Game::getMinimumComponentsId).toList()
        );

        Set<UUID> componentsUUIDs = new HashSet<>();

        for (ComponentsCombo combo : requiredCombos) {
            componentsUUIDs.add(combo.getProcessorId());
            componentsUUIDs.add(combo.getGraphcisCardId());
            componentsUUIDs.add(combo.getRamId());
        }

        List<Component> requiredComponents = componentRepository.getComponentsByUUIDs(componentsUUIDs.stream().toList());

        for (Game game : result) {
            ComponentsCombo minimumReq = requiredCombos.stream()
                    .filter(combo -> combo.getId().equals(game.getMinimumComponentsId()))
                    .findFirst()
                    .orElse(null);

            if (minimumReq == null) {
                continue;
            }

            Component minimumProcessor = requiredComponents.stream()
                    .filter(comp -> comp.getId().equals(minimumReq.getProcessorId()))
                    .findFirst()
                    .orElse(null);
            minimumReq.setProcessor(minimumProcessor);

            Component minimumGraphicsCard = requiredComponents.stream()
                    .filter(comp -> comp.getId().equals(minimumReq.getGraphcisCardId()))
                    .findFirst()
                    .orElse(null);
            minimumReq.setGraphicsCard(minimumGraphicsCard);

            Component minimumRAM = requiredComponents.stream()
                    .filter(comp -> comp.getId().equals(minimumReq.getRamId()))
                    .findFirst()
                    .orElse(null);
            minimumReq.setRam(minimumRAM);

            game.setMinimumComponents(minimumReq);
        }

        return result;
    }

    @Override
    public List<Game> getGames(String name, int releaseYear, ComponentsCombo componentsCombo) {
        return repository.getGames(name, releaseYear, componentsCombo);
    }
}
