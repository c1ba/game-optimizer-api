package com.ciba.gameoptimizerapi.requests;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.models.enums.ComponentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PostComponentRequest {

    @JsonProperty("name")
    @Schema(name = "name",
            description = "The brand of the component along with the type and generation.",
            example = "NVidia GTX 1650",
            type = "String")
    private String name;

    @JsonProperty("capacity")
    @Schema(name = "capacity",
            description = "GHz of the processor, the GBs of the graphics cards or GBs of ram.",
            type = "Float",
            example = "4.00f")
    private float capacity;

    @JsonProperty("type")
    @Schema(name = "type",
            description = "The type of component.",
            type = "processor / graphics_card / ram",
             example = "graphics_card")
    private ComponentEnum.ComponentTypeEnum type;
}
