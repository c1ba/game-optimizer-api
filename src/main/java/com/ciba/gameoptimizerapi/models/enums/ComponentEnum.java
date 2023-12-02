package com.ciba.gameoptimizerapi.models.enums;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;

public class ComponentEnum {
    public enum ComponentTypeEnum {
        PROCESSOR,
        GRAPHICS_CARD,
        RAM
    }

    public static ComponentTypeEnum getType(String value) throws BadRequestException {
        return switch (value) {
            case "PROCESSOR" -> ComponentTypeEnum.PROCESSOR;
            case "GRAPHICS_CARD" -> ComponentTypeEnum.GRAPHICS_CARD;
            case "RAM" -> ComponentTypeEnum.RAM;
            default -> throw new BadRequestException("Not a component type.");
        };
    }

    public static String getLiteral(ComponentTypeEnum enumVal) {
        return switch (enumVal) {
            case PROCESSOR -> "PROCESSOR";
            case GRAPHICS_CARD -> "GRAPHICS_CARD";
            case RAM -> "RAM";
        };
    }
}
