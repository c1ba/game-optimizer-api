/*
 * This file is generated by jOOQ.
 */
package com.ciba.gameoptimizerapi.models.jooq.tables;


import com.ciba.gameoptimizerapi.models.jooq.GamingOptimizer;
import com.ciba.gameoptimizerapi.models.jooq.Keys;
import com.ciba.gameoptimizerapi.models.jooq.tables.records.GamesRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
public class Games extends TableImpl<GamesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>gaming_optimizer.games</code>
     */
    public static final Games GAMES = new Games();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GamesRecord> getRecordType() {
        return GamesRecord.class;
    }

    /**
     * The column <code>gaming_optimizer.games.id</code>.
     */
    public final TableField<GamesRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>gaming_optimizer.games.name</code>.
     */
    public final TableField<GamesRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>gaming_optimizer.games.year_release</code>.
     */
    public final TableField<GamesRecord, Integer> YEAR_RELEASE = createField(DSL.name("year_release"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>gaming_optimizer.games.minimum_components_id</code>.
     */
    public final TableField<GamesRecord, UUID> MINIMUM_COMPONENTS_ID = createField(DSL.name("minimum_components_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>gaming_optimizer.games.created_at</code>.
     */
    public final TableField<GamesRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    private Games(Name alias, Table<GamesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Games(Name alias, Table<GamesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>gaming_optimizer.games</code> table reference
     */
    public Games(String alias) {
        this(DSL.name(alias), GAMES);
    }

    /**
     * Create an aliased <code>gaming_optimizer.games</code> table reference
     */
    public Games(Name alias) {
        this(alias, GAMES);
    }

    /**
     * Create a <code>gaming_optimizer.games</code> table reference
     */
    public Games() {
        this(DSL.name("games"), null);
    }

    public <O extends Record> Games(Table<O> child, ForeignKey<O, GamesRecord> key) {
        super(child, key, GAMES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : GamingOptimizer.GAMING_OPTIMIZER;
    }

    @Override
    public UniqueKey<GamesRecord> getPrimaryKey() {
        return Keys.GAMES_PKEY;
    }

    @Override
    public List<UniqueKey<GamesRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.GAMES_NAME_KEY);
    }

    @Override
    public Games as(String alias) {
        return new Games(DSL.name(alias), this);
    }

    @Override
    public Games as(Name alias) {
        return new Games(alias, this);
    }

    @Override
    public Games as(Table<?> alias) {
        return new Games(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Games rename(String name) {
        return new Games(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Games rename(Name name) {
        return new Games(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Games rename(Table<?> name) {
        return new Games(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, String, Integer, UUID, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super UUID, ? super String, ? super Integer, ? super UUID, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super UUID, ? super String, ? super Integer, ? super UUID, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
