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
public enum UserType implements EnumType {

    user("user"),

    admin("admin");

    private final String literal;

    private UserType(String literal) {
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
        return "user_type";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static UserType lookupLiteral(String literal) {
        return EnumType.lookupLiteral(UserType.class, literal);
    }
}
