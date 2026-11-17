package com.dsa.chat.config;

import org.apache.catalina.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {
    @Bean(name = "chatExecutor", destroyMethod = "shutdown")
    public ExecutorService chatExecutor() {
        return Executors.newFixedThreadPool(4);
    }
}
