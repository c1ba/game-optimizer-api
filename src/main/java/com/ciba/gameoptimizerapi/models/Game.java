package com.ciba.gameoptimizerapi.models;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Game {
    private UUID id;
    private String name;
    private int yearRelease;
    private UUID minimumComponentsId;
    private Timestamp createdAt;

    private ComponentsCombo minimumComponents;
}
