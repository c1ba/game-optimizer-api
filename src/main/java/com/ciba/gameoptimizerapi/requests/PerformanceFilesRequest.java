package com.ciba.gameoptimizerapi.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class PerformanceFilesRequest {

    @JsonProperty("processorId")
    private UUID processorId;

    @JsonProperty("graphicsCardId")
    private UUID graphicsCardId;

    @JsonProperty("ramId")
    private UUID ramId;

    @JsonProperty("gameId")
    private UUID gameId;
}
