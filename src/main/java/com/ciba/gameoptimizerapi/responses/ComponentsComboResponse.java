package com.ciba.gameoptimizerapi.responses;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.ComponentsCombo;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
public class ComponentsComboResponse {

    @JsonProperty("processor")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ComponentResponse processor;

    @JsonProperty("graphicsCard")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ComponentResponse graphicsCard;

    @JsonProperty("ram")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ComponentResponse ram;

    public static ComponentsComboResponse fromComponents(List<Component> components) {
        ComponentsComboResponse response = new ComponentsComboResponse();

        components.stream()
                .filter(elem -> elem.getComponentType().equals(ComponentType.processor))
                .findAny().ifPresent(processor -> response.setProcessor(ComponentResponse.fromComponent(processor)));

        components.stream()
                .filter(elem -> elem.getComponentType().equals(ComponentType.graphics_card))
                .findAny().ifPresent(graphicsCard -> response.setGraphicsCard(ComponentResponse.fromComponent(graphicsCard)));

        components.stream()
                .filter(elem -> elem.getComponentType().equals(ComponentType.ram))
                .findAny().ifPresent(ram -> response.setRam(ComponentResponse.fromComponent(ram)));

        return response;
    }

    public static ComponentsComboResponse fromComponentsCombo(ComponentsCombo result) {
        ComponentsComboResponse response = new ComponentsComboResponse();

        response.setProcessor(ComponentResponse.fromComponent(result.getProcessor()));
        response.setGraphicsCard(ComponentResponse.fromComponent(result.getGraphicsCard()));
        response.setRam(ComponentResponse.fromComponent(result.getRam()));

        return response;
    }
}
