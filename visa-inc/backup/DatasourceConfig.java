package com.visa.config;

import java.beans.PropertyVetoException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.visa.repository"})
public class DatasourceConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase ds = 
        		builder.setType(EmbeddedDatabaseType.H2)
//                .addScript("sql/h2/V1__create_hibernate_sequence.sql")
                .addScript("sql/h2/V3__create_student.sql")
//                .addScript("sql/h2/V10__create_oauth2.sql")
//                .addScript("sql/h2/V11__create_user_authority.sql")
                
                .addScript("sql/h2/V7__insert_student.sql")
//                .addScript("sql/h2/V12__insert_authentication.sql")
//                .addScript("sql/h2/V13__insert_authority.sql")
                .addScript("sql/h2/V14__insert_user.sql")
//                .addScript("sql/h2/V16__create_group.sql")
//                .addScript("sql/h2/V17__insert_user_groups.sql")
                
//                .addScript("sql/h2/V18__create_org.sql")
//                .addScript("sql/h2/V19__insert_org.sql")
                
//                .addScript("sql/h2/V20__create_temp_user.sql")
//                .addScript("sql/h2/V21__insert_temp_user_authority.sql")

                .addScript("sql/h2/V22__create_user_info.sql")
                .addScript("sql/h2/V23__insert_user_info.sql")
                
                .build();
        
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource ds) throws PropertyVetoException{
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(ds);
        entityManagerFactory.setPackagesToScan(new String[]{"com.visa.model"});
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
