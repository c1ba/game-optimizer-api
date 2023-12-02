package com.ciba.gameoptimizerapi.models;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ComponentsCombo {
    private UUID id;
    private UUID processorId;
    private UUID graphcisCardId;
    private UUID ramId;

    private Component processor;
    private Component graphicsCard;
    private Component ram;
}
