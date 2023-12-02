/*
 * This file is generated by jOOQ.
 */
package com.ciba.gameoptimizerapi.models.jooq.enums;


import com.ciba.gameoptimizerapi.models.jooq.GamingOptimizer;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum ComponentType implements EnumType {

    processor("processor"),

    graphics_card("graphics_card"),

    ram("ram");

    private final String literal;

    private ComponentType(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return GamingOptimizer.GAMING_OPTIMIZER;
    }

    @Override
    public String getName() {
        return "component_type";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static ComponentType lookupLiteral(String literal) {
        return EnumType.lookupLiteral(ComponentType.class, literal);
    }
}