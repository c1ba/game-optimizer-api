package com.ciba.gameoptimizerapi.responses;

import com.ciba.gameoptimizerapi.models.Component;
import com.ciba.gameoptimizerapi.models.enums.ComponentEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class ComponentResponse {

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonProperty("type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ComponentEnum.ComponentTypeEnum type;

    @JsonProperty("capacity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float capacity;

    public static ComponentResponse fromComponent(Component result) {
        ComponentResponse response = new ComponentResponse();

        response.setId(result.getId());
        response.setName(result.getName());
        response.setType(ComponentEnum.ComponentTypeEnum.valueOf(result.getType().getLiteral().toUpperCase()));
        response.setCapacity(result.getCapacity());

        return response;
    }
}
