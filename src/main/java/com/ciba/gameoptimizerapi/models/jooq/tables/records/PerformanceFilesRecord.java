/*
 * This file is generated by jOOQ.
 */
package com.ciba.gameoptimizerapi.models.jooq.tables.records;


import com.ciba.gameoptimizerapi.models.jooq.tables.PerformanceFiles;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PerformanceFilesRecord extends UpdatableRecordImpl<PerformanceFilesRecord> implements Record8<UUID, UUID, UUID, UUID, String[], Integer, Integer, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>gaming_optimizer.performance_files.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>gaming_optimizer.performance_files.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for
     * <code>gaming_optimizer.performance_files.components_combo_id</code>.
     */
    public void setComponentsComboId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>gaming_optimizer.performance_files.components_combo_id</code>.
     */
    public UUID getComponentsComboId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>gaming_optimizer.performance_files.user_id</code>.
     */
    public void setUserId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>gaming_optimizer.performance_files.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>gaming_optimizer.performance_files.game_id</code>.
     */
    public void setGameId(UUID value) {
        set(3, value);
    }

    /**
     * Getter for <code>gaming_optimizer.performance_files.game_id</code>.
     */
    public UUID getGameId() {
        return (UUID) get(3);
    }

    /**
     * Setter for
     * <code>gaming_optimizer.performance_files.performance_files_names</code>.
     */
    public void setPerformanceFilesNames(String[] value) {
        set(4, value);
    }

    /**
     * Getter for
     * <code>gaming_optimizer.performance_files.performance_files_names</code>.
     */
    public String[] getPerformanceFilesNames() {
        return (String[]) get(4);
    }

    /**
     * Setter for <code>gaming_optimizer.performance_files.likes</code>.
     */
    public void setLikes(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>gaming_optimizer.performance_files.likes</code>.
     */
    public Integer getLikes() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>gaming_optimizer.performance_files.dislikes</code>.
     */
    public void setDislikes(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>gaming_optimizer.performance_files.dislikes</code>.
     */
    public Integer getDislikes() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>gaming_optimizer.performance_files.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>gaming_optimizer.performance_files.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<UUID, UUID, UUID, UUID, String[], Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<UUID, UUID, UUID, UUID, String[], Integer, Integer, LocalDateTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return PerformanceFiles.PERFORMANCE_FILES.ID;
    }

    @Override
    public Field<UUID> field2() {
        return PerformanceFiles.PERFORMANCE_FILES.COMPONENTS_COMBO_ID;
    }

    @Override
    public Field<UUID> field3() {
        return PerformanceFiles.PERFORMANCE_FILES.USER_ID;
    }

    @Override
    public Field<UUID> field4() {
        return PerformanceFiles.PERFORMANCE_FILES.GAME_ID;
    }

    @Override
    public Field<String[]> field5() {
        return PerformanceFiles.PERFORMANCE_FILES.PERFORMANCE_FILES_NAMES;
    }

    @Override
    public Field<Integer> field6() {
        return PerformanceFiles.PERFORMANCE_FILES.LIKES;
    }

    @Override
    public Field<Integer> field7() {
        return PerformanceFiles.PERFORMANCE_FILES.DISLIKES;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return PerformanceFiles.PERFORMANCE_FILES.CREATED_AT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getComponentsComboId();
    }

    @Override
    public UUID component3() {
        return getUserId();
    }

    @Override
    public UUID component4() {
        return getGameId();
    }

    @Override
    public String[] component5() {
        return getPerformanceFilesNames();
    }

    @Override
    public Integer component6() {
        return getLikes();
    }

    @Override
    public Integer component7() {
        return getDislikes();
    }

    @Override
    public LocalDateTime component8() {
        return getCreatedAt();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getComponentsComboId();
    }

    @Override
    public UUID value3() {
        return getUserId();
    }

    @Override
    public UUID value4() {
        return getGameId();
    }

    @Override
    public String[] value5() {
        return getPerformanceFilesNames();
    }

    @Override
    public Integer value6() {
        return getLikes();
    }

    @Override
    public Integer value7() {
        return getDislikes();
    }

    @Override
    public LocalDateTime value8() {
        return getCreatedAt();
    }

    @Override
    public PerformanceFilesRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord value2(UUID value) {
        setComponentsComboId(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord value3(UUID value) {
        setUserId(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord value4(UUID value) {
        setGameId(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord value5(String[] value) {
        setPerformanceFilesNames(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord value6(Integer value) {
        setLikes(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord value7(Integer value) {
        setDislikes(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord value8(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public PerformanceFilesRecord values(UUID value1, UUID value2, UUID value3, UUID value4, String[] value5, Integer value6, Integer value7, LocalDateTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PerformanceFilesRecord
     */
    public PerformanceFilesRecord() {
        super(PerformanceFiles.PERFORMANCE_FILES);
    }

    /**
     * Create a detached, initialised PerformanceFilesRecord
     */
    public PerformanceFilesRecord(UUID id, UUID componentsComboId, UUID userId, UUID gameId, String[] performanceFilesNames, Integer likes, Integer dislikes, LocalDateTime createdAt) {
        super(PerformanceFiles.PERFORMANCE_FILES);

        setId(id);
        setComponentsComboId(componentsComboId);
        setUserId(userId);
        setGameId(gameId);
        setPerformanceFilesNames(performanceFilesNames);
        setLikes(likes);
        setDislikes(dislikes);
        setCreatedAt(createdAt);
    }
}
