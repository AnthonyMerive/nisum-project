package nisum.user.com.configuration;

import nisum.user.com.controller.auth.JWTGenerator;
import nisum.user.com.controller.auth.JWTValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JWTGenerator jwtGenerator(){
        return new JWTGenerator();
    }

    @Bean
    public JWTValidator jwtDecoder(){
        return new JWTValidator();
    }
}
