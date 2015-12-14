package com.project.app.config.db;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableJpaRepositories("com.project.app.services.repositories")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class PersistenceContext {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource dataSource() throws ClassNotFoundException {
        DataSource ds = new DataSource();

        String url = env.getProperty(SystemSettings.AMTAB_DS_URL);
        String user = env.getProperty(SystemSettings.AMTAB_DS_USERNAME);
        String pass = env.getProperty(SystemSettings.AMTAB_DS_PASSWORD);
        String driver = env.getProperty(SystemSettings.AMTAB_DS_DRIVER);

        // ds.setDriverClassName("org.postgresql.Driver");
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(pass);

        return ds;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.project.app.services.entities");

        Properties jpaProperties = new Properties();

        // Configures the used database dialect. This allows Hibernate to create SQL
        // that is optimized for the used database.
        jpaProperties.put("hibernate.dialect",
                env.getRequiredProperty(SystemSettings.HIBERNATE_DIALECT));

        // Specifies the action that is invoked to the database when the Hibernate
        // SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto",
                env.getRequiredProperty(SystemSettings.HIBERNATE_HBM2DDL));

        // Configures the naming strategy that is used when Hibernate creates
        // new database objects and schema elements
        // jpaProperties.put("hibernate.ejb.naming_strategy",
        // env.getRequiredProperty(SystemSettings.HIBERNATE_NAMING_STRATEGY));

        // If the value of this property is true, Hibernate writes all SQL
        // statements to the console.
        jpaProperties.put("hibernate.show_sql",
                env.getRequiredProperty(SystemSettings.HIBERNATE_SHOW_SQL));

        // If the value of this property is true, Hibernate will format the SQL
        // that is written to the console.
        jpaProperties.put("hibernate.format_sql",
                env.getRequiredProperty(SystemSettings.HIBERNATE_FORMAT_SQL));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    /**
     * Because we are using JPA, we have to create a transaction manager bean that integrates the
     * JPA provider with the Spring transaction mechanism. We can do this by using the
     * JpaTransactionManager class as the transaction manager of our application.
     *
     * We can configure the transaction manager bean by following these steps:
     *
     * -> Create a new JpaTransactionManager object. -> Configure the entity manager factory whose
     * transactions are managed by the created JpaTransactionManager object.
     **/
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
