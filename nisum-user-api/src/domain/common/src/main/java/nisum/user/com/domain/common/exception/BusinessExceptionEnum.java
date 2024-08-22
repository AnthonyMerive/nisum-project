package nisum.user.com.domain.common.exception;

import lombok.Getter;

@Getter
public enum BusinessExceptionEnum {

    USER_EXIST("USER EXIST IN DATABASE"),
    EMAIL_INVALID("THE EMAIL FORMAT IS INVALID"),
    PASSWORD_INVALID("INSECURE PASSWORD"),
    USER_NO_EXIST("USER NO EXIST IN DATABASE"),
    PHONE_NUMBER_EXIST("THE PHONE NUMBER EXIST IN OTHER USER");

    private final String message;

    BusinessExceptionEnum(String message) {
        this.message = message;
    }

}
