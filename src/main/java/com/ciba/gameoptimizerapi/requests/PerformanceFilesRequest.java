package com.ciba.gameoptimizerapi.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class PerformanceFilesRequest {

    @JsonProperty("processorId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "processorId", description = "Component UUID of type processor.", type = "UUID")
    private UUID processorId;

    @JsonProperty("graphicsCardId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "graphicsCardId", description = "Component UUID of type graphics card.", type = "UUID")
    private UUID graphicsCardId;

    @JsonProperty("ramId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "ramId", description = "Component UUID of type ram.", type = "UUID")
    private UUID ramId;

    @JsonProperty("gameId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "gameId", description = "Game UUID.", type = "UUID")
    private UUID gameId;
}
