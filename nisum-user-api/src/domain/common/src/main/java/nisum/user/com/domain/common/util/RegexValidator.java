package nisum.user.com.domain.common.util;

import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Pattern;

public class RegexValidator {

    @Value("${regexp.password}")
    private String passwordRegex;

    @Value("${regexp.email}")
    private String emailRegex;

    public boolean isInvalidEmail(String email) {

        var emailPattern = Pattern.compile(emailRegex);
        return !emailPattern.matcher(email).matches();
    }

    public boolean isInvalidPassword(String password) {

        var passwordPattern = Pattern.compile(passwordRegex);
        return !passwordPattern.matcher(password).matches();
    }
}
