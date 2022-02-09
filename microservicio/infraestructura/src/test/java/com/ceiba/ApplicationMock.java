package com.ceiba;

import java.io.IOException;

import javax.sql.DataSource;

import org.flywaydb.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@ComponentScan("com.ceiba")
public class ApplicationMock {
	

    @Bean
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) throws IOException {

        return Flyway.configure().locations("filesystem:../src/main/resources/db/migration/DML","filesystem:/src/main/resources/db/migration/DML").baselineOnMigrate(true)
                .dataSource(dataSource).load();

    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
	
}
