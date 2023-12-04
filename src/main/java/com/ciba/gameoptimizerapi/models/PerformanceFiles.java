package com.ciba.gameoptimizerapi.models;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PerformanceFiles {
    private UUID id;
    private UUID componentsComboId;
    private UUID userId;
    private UUID gameId;
    private String[] performanceFilesNames;
    private int likes;
    private int dislikes;
    private Timestamp createdAt;

    private ComponentsCombo componentsCombo;
    private User user;
    private Game game;
}
