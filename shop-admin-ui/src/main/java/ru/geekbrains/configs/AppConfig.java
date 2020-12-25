package ru.geekbrains.configs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import utils.DateFormatter;

@Configuration
@EnableTransactionManagement
//@SpringBootApplication(scanBasePackages={"ru.geekbrains"})
public class AppConfig {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

}
