package com.ciba.gameoptimizerapi.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PerformanceFilesResponse {

    @JsonProperty("files")
    List<FileResponse> files;
}
