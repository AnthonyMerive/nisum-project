package nisum.user.com.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NISUM Users Magnament API")
                        .version("0.1")
                        .description("NISUM API Documentation")
                        .contact(new Contact()
                                .name("Anthony Jose Colmenares Rivas")
                                .email("anthonycolmenaresrivas@gmail.com")));
    }
}
