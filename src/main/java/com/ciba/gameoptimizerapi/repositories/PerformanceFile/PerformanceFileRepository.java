package com.ciba.gameoptimizerapi.repositories.PerformanceFile;

import com.ciba.gameoptimizerapi.models.PerformanceFiles;

import java.util.List;
import java.util.UUID;

public interface PerformanceFileRepository {

    List<PerformanceFiles> getPerformanceFilesNamesByComponentsComboAndGame(UUID comboUUID, UUID gameUUID);
}
