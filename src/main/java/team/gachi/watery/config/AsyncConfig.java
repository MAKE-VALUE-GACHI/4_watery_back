package team.gachi.watery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(1); // 최소 스레드 수
        executor.setMaxPoolSize(2); // 최대 스레드 수
        executor.setQueueCapacity(10); // 대기 큐 용량
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(10);

        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor(); // 위에서 정의한 executor를 사용
    }
}