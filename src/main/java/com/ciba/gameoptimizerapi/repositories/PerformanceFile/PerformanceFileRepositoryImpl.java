package com.ciba.gameoptimizerapi.repositories.PerformanceFile;

import com.ciba.gameoptimizerapi.models.PerformanceFiles;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.ciba.gameoptimizerapi.models.jooq.Tables.PERFORMANCE_FILES;

@Repository
@RequiredArgsConstructor
public class PerformanceFileRepositoryImpl implements PerformanceFileRepository {

    private final DSLContext dsl;

    @Override
    public List<PerformanceFiles> getPerformanceFilesNamesByComponentsComboAndGame(UUID comboUUID, UUID gameUUID) {
        return dsl.selectFrom(PERFORMANCE_FILES)
                .where(PERFORMANCE_FILES.COMPONENTS_COMBO_ID.eq(comboUUID))
                .and(PERFORMANCE_FILES.GAME_ID.eq(gameUUID))
                .fetchInto(PerformanceFiles.class);
    }
}
