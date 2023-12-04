package com.ciba.gameoptimizerapi.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID processorId;

    @JsonProperty("graphicsCardId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID graphicsCardId;

    @JsonProperty("ramId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID ramId;

    @JsonProperty("gameId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID gameId;
}
