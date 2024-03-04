package nisum.user.com.configuration;

import nisum.user.com.domain.common.port.TraceRepositoryPort;
import nisum.user.com.domain.implementation.TraceMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public TraceMessageService traceMessageService(TraceRepositoryPort traceRepositoryPort) {
        return new TraceMessageService(traceRepositoryPort);
    }
}
