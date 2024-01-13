package com.ciba.gameoptimizerapi.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {
    @JsonProperty("username")
    @Schema(description = "Username you want to log in with.", name = "username", type = "String")
    private String username;

    @JsonProperty("password")
    @Schema(description = "Password you want to log in with.", name = "password", type = "String")
    private String password;
}
