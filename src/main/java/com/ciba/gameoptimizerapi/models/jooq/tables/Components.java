/*
 * This file is generated by jOOQ.
 */
package com.ciba.gameoptimizerapi.models.jooq.tables;


import com.ciba.gameoptimizerapi.models.jooq.GamingOptimizer;
import com.ciba.gameoptimizerapi.models.jooq.Keys;
import com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType;
import com.ciba.gameoptimizerapi.models.jooq.tables.records.ComponentsRecord;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Components extends TableImpl<ComponentsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>gaming_optimizer.components</code>
     */
    public static final Components COMPONENTS = new Components();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ComponentsRecord> getRecordType() {
        return ComponentsRecord.class;
    }

    /**
     * The column <code>gaming_optimizer.components.id</code>.
     */
    public final TableField<ComponentsRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>gaming_optimizer.components.name</code>.
     */
    public final TableField<ComponentsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).defaultValue(DSL.field("NULL::character varying", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>gaming_optimizer.components.type</code>.
     */
    public final TableField<ComponentsRecord, ComponentType> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(com.ciba.gameoptimizerapi.models.jooq.enums.ComponentType.class), this, "");

    /**
     * The column <code>gaming_optimizer.components.capacity</code>.
     */
    public final TableField<ComponentsRecord, Float> CAPACITY = createField(DSL.name("capacity"), SQLDataType.REAL, this, "");

    /**
     * The column <code>gaming_optimizer.components.created_at</code>.
     */
    public final TableField<ComponentsRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    private Components(Name alias, Table<ComponentsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Components(Name alias, Table<ComponentsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>gaming_optimizer.components</code> table
     * reference
     */
    public Components(String alias) {
        this(DSL.name(alias), COMPONENTS);
    }

    /**
     * Create an aliased <code>gaming_optimizer.components</code> table
     * reference
     */
    public Components(Name alias) {
        this(alias, COMPONENTS);
    }

    /**
     * Create a <code>gaming_optimizer.components</code> table reference
     */
    public Components() {
        this(DSL.name("components"), null);
    }

    public <O extends Record> Components(Table<O> child, ForeignKey<O, ComponentsRecord> key) {
        super(child, key, COMPONENTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : GamingOptimizer.GAMING_OPTIMIZER;
    }

    @Override
    public UniqueKey<ComponentsRecord> getPrimaryKey() {
        return Keys.COMPONENTS_PKEY;
    }

    @Override
    public Components as(String alias) {
        return new Components(DSL.name(alias), this);
    }

    @Override
    public Components as(Name alias) {
        return new Components(alias, this);
    }

    @Override
    public Components as(Table<?> alias) {
        return new Components(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Components rename(String name) {
        return new Components(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Components rename(Name name) {
        return new Components(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Components rename(Table<?> name) {
        return new Components(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, String, ComponentType, Float, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super UUID, ? super String, ? super ComponentType, ? super Float, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super UUID, ? super String, ? super ComponentType, ? super Float, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
