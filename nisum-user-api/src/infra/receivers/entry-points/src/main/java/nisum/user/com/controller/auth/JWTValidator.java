package nisum.user.com.controller.auth;

import lombok.extern.java.Log;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;


@Log
public class JWTValidator {

    @Value("${jwt.subject}")
    private String expectedSubject;

    public boolean isValidToken(String authorizationHeader) {

        if (authorizationHeader == null) return false;
        if (!authorizationHeader.startsWith("Bearer ")) return false;

        var decodedJWT = getSubjectAndExpiration(authorizationHeader.substring(7));

        if (decodedJWT.isEmpty()) return false;

        var jsonJwt = new JSONObject(decodedJWT);

        return jsonJwt.getString("sub").equals(expectedSubject)
                && new Date().before(new Date(jsonJwt.getLong("exp") * 1000));
    }

    private String getSubjectAndExpiration(String jwt) {

        Base64.Decoder decoder = Base64.getUrlDecoder();
        try {
            String[] chunks = jwt.split("\\.");
            return new String(decoder.decode(chunks[1]));

        } catch (Exception e) {
            log.log(Level.WARNING, "Error al decodificar token: " + e.getMessage());
            return "";
        }
    }
}
