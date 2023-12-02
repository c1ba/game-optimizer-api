package com.ciba.gameoptimizerapi.models;

import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Component {

    private UUID id;
    private String name;
    private float capacity;
    private ComponentType componentType;
    private Timestamp createdAt;
}
