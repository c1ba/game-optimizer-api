package com.ciba.gameoptimizerapi.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class SortedComponentsResponse {

    @JsonProperty("processors")
    private List<ComponentResponse> processors = new ArrayList<>();

    @JsonProperty("graphicCards")
    private List<ComponentResponse> graphicCards = new ArrayList<>();

    @JsonProperty("rams")
    private List<ComponentResponse> rams = new ArrayList<>();

    public static SortedComponentsResponse sortComponentResponses(List<ComponentResponse> results) {
        SortedComponentsResponse response = new SortedComponentsResponse();

        for (ComponentResponse result : results) {
            switch (result.getType()) {
                case PROCESSOR -> response.getProcessors().add(result);
                case GRAPHICS_CARD -> response.getGraphicCards().add(result);
                case RAM -> response.getRams().add(result);
            }
        }

        return response;
    }
}
