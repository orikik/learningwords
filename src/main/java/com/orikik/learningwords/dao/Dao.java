package com.orikik.learningwords.dao;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectLimitPercentStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.jooq.impl.DSL.field;

public abstract class Dao<T> {
    protected static final ImprovedNamingStrategy NAMING_STRATEGY = new ImprovedNamingStrategy();

    private final Class<T> clazz;
    @Autowired
    private DSLContext dsl;

    public Dao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public DSLContext getDsl() {
        return dsl;
    }

    public List<T> fetchInto(SelectLimitPercentStep<Record> select) {
        return select.fetchInto(clazz);
    }

    public T fetch(SelectLimitPercentStep<Record> select) {
        return select.fetchOne().into(clazz);
    }

    protected Field<Object> getField(String table, String propertyName) {
        return field(columnName(table, propertyName));
    }

    protected String columnName(String table, String propertyName) {
        String columnName = NAMING_STRATEGY.propertyToColumnName(propertyName);
        return StringUtils.isEmpty(table)
                ? columnName
                : table + "." + columnName;
    }
}
