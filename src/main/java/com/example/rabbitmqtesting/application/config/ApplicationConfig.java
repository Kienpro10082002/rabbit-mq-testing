package com.example.rabbitmqtesting.application.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbit-mq-testing")
@Getter
@Setter
public class ApplicationConfig {

    private RabbitMq rabbitMq = new RabbitMq();

    @Getter
    @Setter
    public static class RabbitMq {
        private String projectName;
        private String projectOwner;
    }

}
