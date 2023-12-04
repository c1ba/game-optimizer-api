package com.ciba.gameoptimizerapi.services.PerformanceFile;


import com.ciba.gameoptimizerapi.models.PerformanceFiles;
import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;

import java.util.List;

public interface PerformanceFileService {

    List<PerformanceFiles> getPerformanceFiles(PerformanceFilesRequest request);
}
