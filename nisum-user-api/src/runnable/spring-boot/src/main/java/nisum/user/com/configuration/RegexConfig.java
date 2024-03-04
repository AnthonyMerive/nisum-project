package nisum.user.com.configuration;

import nisum.user.com.domain.common.util.RegexValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegexConfig {

    @Bean
    public RegexValidator regexValidator() {
        return new RegexValidator();
    }
}
