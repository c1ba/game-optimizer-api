package com.ciba.gameoptimizerapi.requests;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.enums.ComponentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PostComponentRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("capacity")
    private float capacity;

    @JsonProperty("type")
    private ComponentEnum.ComponentTypeEnum type;

    public PostComponentRequest(String name, float capacity, String type) throws BadRequestException {
        this.capacity = capacity;
        this.name = name;
        this.type = ComponentEnum.getType(type);
    }
}
