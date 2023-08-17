package com.example.store.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig {

//    https://habr.com/ru/articles/580062/
//    https://www.baeldung.com/spring-scheduled-tasks
//    https://stackoverflow.com/questions/67535773/how-to-retry-scheduled-task-in-spring

}
