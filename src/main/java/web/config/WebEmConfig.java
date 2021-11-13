//package web.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
////import org.springframework.jdbc.datasource.DriverManagerDataSource;
////import org.springframework.orm.jpa.JpaTransactionManager;
////import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
////import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
////import org.springframework.transaction.PlatformTransactionManager;
////import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@PropertySource("classpath:db.properties")
//@EnableTransactionManagement(proxyTargetClass = true)
//@ComponentScan(basePackages = "web")
//public class WebEmConfig implements WebMvcConfigurer {
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("db.driver"));
//        dataSource.setUrl(env.getProperty("db.url"));
//        dataSource.setUsername(env.getProperty("db.username"));
//        dataSource.setPassword(env.getProperty("db.password"));
//        return dataSource;
//    }
//
//    @Bean
//        public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
//
//            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//            vendorAdapter.setGenerateDdl(true);
//            vendorAdapter.setShowSql(true);
//            Properties properties = new Properties();
//            properties.setProperty("hibernate.hbm2ddl.auto", "update");
//            properties.setProperty("hibernate.connection.dialect", "org.hibernate.dialect.MySQL5Dialect");
//            properties.setProperty("hibernate.connection.CharSet", "utf-8");
//            properties.setProperty("hibernate.connection.useUnicode", "true");
//            properties.setProperty("hibernate.connection.characterEncoding", "utf-8");
//
//            LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//            factory.setJpaVendorAdapter(vendorAdapter);
//            factory.setPackagesToScan("web.model");
//            factory.setDataSource(getDataSource());
//            factory.setJpaProperties(properties);
//
//            return factory;
//        }
//
//        @Bean
//        public PlatformTransactionManager transactionManager () {
//
//            JpaTransactionManager txManager = new JpaTransactionManager();
//            txManager.setEntityManagerFactory(entityManagerFactory().getObject());
//            return txManager;
//        }
//}
