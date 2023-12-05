package com.ciba.gameoptimizerapi.repositories.Component;

import com.ciba.gameoptimizerapi.models.Component;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.COMPONENTS;

@Repository
@RequiredArgsConstructor
public class ComponentRepositoryImpl implements ComponentRepository {

    private final DSLContext dsl;

    @Override
    public List<Component> getComponentsByUUIDs(List<UUID> idList) {
        return dsl.selectFrom(COMPONENTS)
                .where(COMPONENTS.ID.in(idList))
                .fetchInto(Component.class);
    }

    @Override
    public List<Component> getAllComponents() {
        return dsl.selectFrom(COMPONENTS)
                .fetchInto(Component.class);
    }
}
