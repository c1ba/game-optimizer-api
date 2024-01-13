package com.ciba.gameoptimizerapi.repositories.ComponentsCombo;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.COMPONENT_COMBOS;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComponentsComboRepositoryImpl implements ComponentsComboRepository {

    private final DSLContext dsl;

    @Override
    public List<ComponentsCombo> getComponentCombosByUUIDs(List<UUID> uuidList) {
        return dsl.selectFrom(COMPONENT_COMBOS)
                .where(COMPONENT_COMBOS.ID.in(uuidList))
                .fetchInto(ComponentsCombo.class);
    }

    @Override
    public Optional<ComponentsCombo> getComponentsComboByComponents(UUID processorUUID, UUID graphicsCardUUID, UUID ramUUID) {
        return dsl.selectFrom(COMPONENT_COMBOS)
                .where(COMPONENT_COMBOS.PROCESSOR_ID.eq(processorUUID))
                .and(COMPONENT_COMBOS.GRAPHICS_CARD_ID.eq(graphicsCardUUID))
                .and(COMPONENT_COMBOS.RAM_ID.eq(ramUUID))
                .fetchOptionalInto(ComponentsCombo.class);
    }

    @Override
    public void create(ComponentsCombo data) {
        try {
            dsl.insertInto(COMPONENT_COMBOS,
                            COMPONENT_COMBOS.ID,
                            COMPONENT_COMBOS.PROCESSOR_ID,
                            COMPONENT_COMBOS.GRAPHICS_CARD_ID,
                            COMPONENT_COMBOS.RAM_ID)
                    .values(data.getId(),
                            data.getProcessorId(),
                            data.getGraphcisCardId(),
                            data.getRamId())
                    .execute();
        }
        catch (Exception ex) {
            log.error("Repo error: ", ex);
        }
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        try {
            dsl.deleteFrom(COMPONENT_COMBOS)
                    .where(COMPONENT_COMBOS.ID.eq(uuid))
                    .execute();
        }
        catch (Exception ex) {
            log.error("Repo exception: ", ex);
        }
    }
}
