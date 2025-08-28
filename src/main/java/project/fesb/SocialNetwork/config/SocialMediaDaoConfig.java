package project.fesb.SocialNetwork.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class SocialMediaDaoConfig {
    @Bean(name = "liquibaseDataSource")
    @ConfigurationProperties(prefix = "liquibase.datasource")
    public DataSource liquibaseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
