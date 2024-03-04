package nisum.user.com.controller;

import lombok.extern.java.Log;
import nisum.user.com.controller.auth.JWTGenerator;
import nisum.user.com.controller.dto.ResponseDTO;
import nisum.user.com.controller.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Log
@RestController
@RequestMapping(value = "auth/api/v1")
public record AuthController(JWTGenerator jwtGenerator) implements ResponseBuilder {

    @GetMapping("/token")
    public ResponseEntity<ResponseDTO<String>> getJwtToken(
            @RequestHeader("Secret") String secret) {

        try {

            var jwtGenerated = jwtGenerator.generate(secret);

            if (Objects.isNull(jwtGenerated)) return build401Response();

            return build200Response(jwtGenerated, "Success get");

        } catch (Exception ex) {

            return build500Response(ex.getMessage());

        }
    }
}
