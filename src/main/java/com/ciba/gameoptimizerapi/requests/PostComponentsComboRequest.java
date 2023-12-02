package com.ciba.gameoptimizerapi.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PostComponentsComboRequest {

    @JsonProperty("processor")
    private PostComponentRequest processor;

    @JsonProperty("graphicsCard")
    private PostComponentRequest graphicsCard;

    @JsonProperty("ram")
    private PostComponentRequest ram;
}
