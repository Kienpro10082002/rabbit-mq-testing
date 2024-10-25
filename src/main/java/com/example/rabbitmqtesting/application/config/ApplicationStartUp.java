package com.example.rabbitmqtesting.application.config;

import com.example.rabbitmqtesting.application.utils.WriteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationStartUp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        List<String> errList = new ArrayList<>();

        if(applicationConfig.getRabbitMq() == null) {
            errList.add("Miss config rabbit-mq-project");
        }

        if(!errList.isEmpty()) {
            WriteLog.error("", "RabbitMqTestingApplication", "main", String.join("\n", errList), new Exception());
            System.exit(0);
        }

        String msg = "########## STARTING RABBITMQ TESTING ##########"
                + MessageFormat.format("\n - Active profile: {0}",
                String.join(", ", this.environment.getActiveProfiles()))
                + MessageFormat.format("\n - Project Name: {0}", applicationConfig.getRabbitMq().getProjectName())
                + MessageFormat.format("\n - Project Owner: {0}", applicationConfig.getRabbitMq().getProjectOwner());

        WriteLog.write("INFO", msg);
    }

}
