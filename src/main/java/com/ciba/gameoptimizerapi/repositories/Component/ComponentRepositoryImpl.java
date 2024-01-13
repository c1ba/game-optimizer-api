package com.ciba.gameoptimizerapi.repositories.Component;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.COMPONENTS;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ComponentRepositoryImpl implements ComponentRepository {

    private final DSLContext dsl;

    @Override
    public List<Component> getComponentsByUUIDs(List<UUID> idList) {
        List<Component> result = new ArrayList<>();
        try {
            result = dsl.selectFrom(COMPONENTS)
                    .where(COMPONENTS.ID.in(idList))
                    .fetchInto(Component.class);
        }
        catch (Exception ex) {
            log.error("Repo exception: ", ex);
        }
        return result;
    }

    @Override
    public List<Component> getAllComponents() {
        List<Component> result = new ArrayList<>();
        try {
            result = dsl.selectFrom(COMPONENTS)
                    .fetchInto(Component.class);
        }
        catch (Exception ex) {
            log.error("Repo exception: ", ex);
        }
        return result;
    }

    @Override
    public void create(Component data) {
        try {
            dsl.insertInto(COMPONENTS,
                            COMPONENTS.ID,
                            COMPONENTS.NAME,
                            COMPONENTS.TYPE,
                            COMPONENTS.CAPACITY)
                    .values(data.getId(),
                            data.getName(),
                            data.getType(),
                            data.getCapacity())
                    .execute();
        }
        catch (Exception ex) {
            log.error("Repo exception: ", ex);
        }
    }

    @Override
    public List<Component> getComponentsByNamesAndCapacities(List<Component> data) {
        List<Component> result = new ArrayList<>();
        try {
            dsl.transaction(transaction -> {
                for (Component dataElem : data) {
                    Optional<Component> existing;
                    if (List.of(ComponentType.graphics_card, ComponentType.processor).contains(dataElem.getType())) {
                        existing = transaction.dsl()
                                .selectFrom(COMPONENTS)
                                .where(COMPONENTS.NAME.eq(dataElem.getName()))
                                .and(COMPONENTS.CAPACITY.eq(dataElem.getCapacity()))
                                .fetchOptionalInto(Component.class);
                    }
                    else {
                        existing = transaction.dsl()
                                .selectFrom(COMPONENTS)
                                .where(COMPONENTS.TYPE.eq(ComponentType.ram))
                                .and(COMPONENTS.CAPACITY.eq(dataElem.getCapacity()))
                                .fetchOptionalInto(Component.class);
                    }

                    existing.ifPresent(result::add);
                }
            });
        }
        catch (Exception ex) {
            log.error("Repo exception: ", ex);
        }
        return result;
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        try {
            dsl.deleteFrom(COMPONENTS)
                    .where(COMPONENTS.ID.eq(uuid))
                    .execute();
        }
        catch (Exception ex) {
            log.error("Repo exception: ", ex);
        }
    }
}
