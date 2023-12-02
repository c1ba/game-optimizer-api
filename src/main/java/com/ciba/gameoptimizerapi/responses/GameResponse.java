package com.ciba.gameoptimizerapi.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class GameResponse {

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonProperty("releaseYear")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int releaseYear;

    @JsonProperty("minimumComponents")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ComponentsComboResponse minimumComponents;
}
