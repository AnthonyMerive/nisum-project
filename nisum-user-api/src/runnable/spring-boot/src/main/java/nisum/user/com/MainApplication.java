package nisum.user.com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.h2.tools.Server.openBrowser;

@SpringBootApplication
public class MainApplication {

    private static String swaggerPath;
    private static String port;

    @Value("${springdoc.swagger-ui.path}")
    public void setSwaggerPath(String swaggerPath) {
        MainApplication.swaggerPath = swaggerPath;
    }

    @Value("${server.port}")
    public void setPort(String port) {
        MainApplication.port = port;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
        String url = "http://localhost:" + port + swaggerPath;
        openBrowser(url);
    }
}