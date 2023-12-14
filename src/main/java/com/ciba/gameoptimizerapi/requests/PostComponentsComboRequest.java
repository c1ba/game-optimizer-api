package com.ciba.gameoptimizerapi.requests;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Component> extractComponentsData(PostComponentsComboRequest request) {
        List<Component> result = new ArrayList<>();

        result.add(Component.builder()
                        .type(ComponentType.processor)
                        .name(request.getProcessor().getName())
                        .capacity(request.getProcessor().getCapacity())
                .build());

        result.add(Component.builder()
                .type(ComponentType.graphics_card)
                .name(request.getGraphicsCard().getName())
                .capacity(request.getGraphicsCard().getCapacity())
                .build());

        result.add(Component.builder()
                .type(ComponentType.ram)
                .name(request.getRam().getName())
                .capacity(request.getRam().getCapacity())
                .build());

        return result;
    }
}
