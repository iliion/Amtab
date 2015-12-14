package com.project.app.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class SystemSettings implements Settings {

    final static Logger logger = LoggerFactory.getLogger(SystemSettings.class);

    public static final String AMTAB_DS_DRIVER = "datasource.amtab.driverClassName";
    public static final String AMTAB_DS_URL = "datasource.amtab.url";
    public static final String AMTAB_DS_USERNAME = "datasource.amtab.username";
    public static final String AMTAB_DS_PASSWORD = "datasource.amtab.password";

    public static final String HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";
    public static final String HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

    @Configuration
    @Profile("dev")
    @PropertySources({
            @PropertySource("classpath:application-dev.properties"),
            @PropertySource(value = "file:config/application-dev.properties", ignoreResourceNotFound = true) })
    static class Dev {
    }

    @Autowired
    private Environment env;

    @Override
    public String get(String key) {
        logger.info("Was asked for system setting: {}. Got: {}", key, env.getProperty(key));
        return env.getProperty(key);
    }

    public String[] getActiveProfiles() {
        return env.getActiveProfiles();
    }

    public String[] getDefaultProfiles() {
        return env.getDefaultProfiles();
    }

}
