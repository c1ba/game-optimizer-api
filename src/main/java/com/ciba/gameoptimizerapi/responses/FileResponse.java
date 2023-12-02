package com.ciba.gameoptimizerapi.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FileResponse {

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonProperty("link")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String link;
}
