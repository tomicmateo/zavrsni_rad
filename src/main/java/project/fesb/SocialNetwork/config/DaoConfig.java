package project.fesb.SocialNetwork.config;




//import hr.elevatio.fileshare.rest.LoggingClientHttpRequestInterceptor;
//import hr.elevatio.fileshare.security.SecurityConfiguration;
import liquibase.integration.spring.SpringLiquibase;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.*;

@Configuration
// @Import({ SecurityConfiguration.class })
@EnableJpaRepositories(entityManagerFactoryRef = "appEntityManagerFactory", basePackages = {
        "project.fesb.SocialNetwork.dao.jpa.app" })
public class DaoConfig {

    private final static Logger LOG = LoggerFactory.getLogger(DaoConfig.class);
    private final int maxConnectionsTotal;
    private final int maxConnectionsPerRoute;
    // private final LoggingClientHttpRequestInterceptor
    // loggingClientHttpRequestInterceptor;
    // private final String appDefaultSchema;
    private final String liquibaseChangelog;
    private final String liquibaseChangelogTable;
    private final String liquibaseChangelogLockTable;
    private final String liquibaseContexts;
    private final String applicationUser;
    private final DataSource liquibaseDataSource;

    public DaoConfig(final @Value("${http.client.maxConnectionsTotal:200}") int maxConnectionsTotal,
                     final @Value("${http.client.maxConnectionsPerRoute:20}") int maxConnectionsPerRoute,
                     // final LoggingClientHttpRequestInterceptor
                     // loggingClientHttpRequestInterceptor,
                     // final @Value("${app.default_schema}") String appDefaultSchema,
                     final @Value("${spring.liquibase.change-log}") String liquibaseChangelog,
                     final @Value("${spring.liquibase.database-change-log-table}") String liquibaseChangelogTable,
                     final @Value("${spring.liquibase.database-change-log-lock-table}") String liquibaseChangelogLockTable,
                     final @Value("${spring.liquibase.contexts}") String liquibaseContexts,
                     final @Value("${spring.datasource.username}") String applicationUser,
                     final @Qualifier("liquibaseDataSource") DataSource liquibaseDataSource) {
        this.maxConnectionsTotal = maxConnectionsTotal;
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
        // this.loggingClientHttpRequestInterceptor =
        // loggingClientHttpRequestInterceptor;
        // this.appDefaultSchema = appDefaultSchema;
        this.liquibaseChangelog = liquibaseChangelog;
        this.liquibaseChangelogTable = liquibaseChangelogTable;
        this.liquibaseChangelogLockTable = liquibaseChangelogLockTable;
        this.liquibaseContexts = liquibaseContexts;
        this.applicationUser = applicationUser;
        this.liquibaseDataSource = liquibaseDataSource;
    }

    // @Bean("restTemplate")
    // public RestTemplate restTemplate() {
    // final RestTemplate ret = new
    // RestTemplate(httpComponentsClientHttpRequestFactory());
    // final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    //// interceptors.add(loggingClientHttpRequestInterceptor);
    // ret.setInterceptors(interceptors);
    // return ret;
    // }

    // @Bean("httpComponentsClientHttpRequestFactory")
    // public HttpComponentsClientHttpRequestFactory
    // httpComponentsClientHttpRequestFactory() {
    // return new HttpComponentsClientHttpRequestFactory(httpClient());
    // }

    // @Bean
    // public HttpClient httpClient() {
    // final PoolingHttpClientConnectionManager clientConnectionManager = new
    // PoolingHttpClientConnectionManager();
    // LOG.debug("Setting maxTotal to {}", maxConnectionsTotal);
    // clientConnectionManager.setMaxTotal(maxConnectionsTotal);
    // LOG.debug("Setting defaultMaxPerRoute to {}", maxConnectionsPerRoute);
    // clientConnectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);
    // final HttpClientBuilder httpClientBuilder = HttpClients.custom();
    // httpClientBuilder.setConnectionManager(clientConnectionManager);
    // return httpClientBuilder.build();
    // }

    @Primary
    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final EntityManagerFactoryBuilder builder,
            final @Qualifier("dataSource") DataSource dataSource) {
        final Map<String, Object> properties = new HashMap<>();
        return builder.dataSource(dataSource)
                .packages("project.fesb.SocialNetwork.model.jpa.app")
                .persistenceUnit("app")
                .properties(properties)
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(liquibaseDataSource);
        builder.scanPackages("project.fesb.SocialNetwork.model.jpa.app")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    // transaction manager
    @Bean(name = "transactionManager")
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        return prop;
    }

    @Bean
    public SpringLiquibase liquibase() {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(liquibaseDataSource);
        liquibase.setChangeLog(liquibaseChangelog);
        liquibase.setDatabaseChangeLogLockTable(liquibaseChangelogLockTable);
        liquibase.setDatabaseChangeLogTable(liquibaseChangelogTable);
        liquibase.setContexts(liquibaseContexts);
        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put("dbUser", applicationUser);
        liquibase.setChangeLogParameters(parameters);
        return liquibase;
    }

}
