package com.ciba.gameoptimizerapi.repositories.Game;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.Game;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.COMPONENT_COMBOS;
import static com.ciba.gameoptimizerapi.models.jooq.Tables.GAMES;

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final DSLContext dsl;

    @Override
    public List<Game> getGames(String name, Integer releaseYear, ComponentsCombo componentsCombo) {
        if (name == null && releaseYear == null && componentsCombo == null) {
            return dsl.selectFrom(GAMES)
                    .fetchInto(Game.class);
        }

        return dsl.select().from(GAMES)
                .join(COMPONENT_COMBOS)
                .on(COMPONENT_COMBOS.ID.eq(GAMES.MINIMUM_COMPONENTS_ID))
                .where(GAMES.NAME.like(name))
                .or(GAMES.YEAR_RELEASE.eq(releaseYear))
                .or(GAMES.MINIMUM_COMPONENTS_ID.eq(componentsCombo.getId()))
                .or(COMPONENT_COMBOS.PROCESSOR_ID.eq(componentsCombo.getProcessorId()))
                .or(COMPONENT_COMBOS.GRAPHICS_CARD_ID.eq(componentsCombo.getGraphcisCardId()))
                .or(COMPONENT_COMBOS.RAM_ID.eq(componentsCombo.getRamId()))
                .fetchInto(Game.class);
    }


}
