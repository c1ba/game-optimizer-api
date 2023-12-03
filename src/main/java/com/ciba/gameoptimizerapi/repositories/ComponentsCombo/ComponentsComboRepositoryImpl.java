package com.ciba.gameoptimizerapi.repositories.ComponentsCombo;

import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.COMPONENT_COMBOS;

@Repository
@RequiredArgsConstructor
public class ComponentsComboRepositoryImpl implements ComponentsComboRepository {

    private final DSLContext dsl;

    @Override
    public List<ComponentsCombo> getComponentCombosByUUIDs(List<UUID> uuidList) {
        return dsl.selectFrom(COMPONENT_COMBOS)
                .where(COMPONENT_COMBOS.ID.in(uuidList))
                .fetchInto(ComponentsCombo.class);
    }
}
