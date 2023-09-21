package com.example.store.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig {


//    @Bean
//    public LockProvider lockProvider(RedisConnectionFactory connectionFactory) {
//        return new RedisLockProvider(connectionFactory, "your-lock-name");
//    }

    @Bean
    public LockRegistry lockRegistry(RedisConnectionFactory connectionFactory) {
        return new RedisLockRegistry(connectionFactory, "store");
    }



//    https://habr.com/ru/articles/580062/
//    https://www.baeldung.com/spring-scheduled-tasks
//    https://stackoverflow.com/questions/67535773/how-to-retry-scheduled-task-in-spring

}
