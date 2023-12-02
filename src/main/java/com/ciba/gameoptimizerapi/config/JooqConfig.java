package com.ciba.gameoptimizerapi.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {
    private final DataSource dataSource;

    @Autowired
    public JooqConfig(DataSource dataSource) {this.dataSource = dataSource;}

    @Bean
    public DSLContext dslContext() {return DSL.using(dataSource, SQLDialect.POSTGRES);}

    @Bean
    public JdbcTemplate jdbcTemplate() {return new JdbcTemplate(dataSource);}
}
