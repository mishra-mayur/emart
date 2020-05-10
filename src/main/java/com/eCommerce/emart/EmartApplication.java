package com.eCommerce.emart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.eCommerce.emart.repository"})
public class EmartApplication {

	private static final String SEND_EMAIL = "sendEmail";

	@Value("${e_mart.async.process.core.pool.size:3}")
	private int corePoolSize;
	@Value("${e_mart.async.process.queue.capacity:500}")
	private int queueCapacity;
	@Value("${e_mart.async.process.max.pool.size:15}")
	private int maxPoolSize;

	public static void main(String[] args) {
		SpringApplication.run(EmartApplication.class, args);
	}

	private Executor createExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.initialize();
		return executor;
	}

	@Bean(name = SEND_EMAIL)
	public Executor sendEmailAsync() {
		return createExecutor();
	}
}
