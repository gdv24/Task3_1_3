package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import web.config.WebConfig;
//import web.config.WebEmConfig;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationEntry extends SpringBootServletInitializer {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationEntry.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationEntry.class);
    }
}