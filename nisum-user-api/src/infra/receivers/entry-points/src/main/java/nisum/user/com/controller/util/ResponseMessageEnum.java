package nisum.user.com.controller.util;

import lombok.Getter;

@Getter
public enum ResponseMessageEnum {

    SUCCESS("Success"),
    USER_NOT_FOUND("User not found"),
    INVALID_TOKEN("Invalid Token"),
    UNHANDLED_EXCEPTION("Unhandled exception: "),
    NO_ERROR_DESCRIPTION("Error no have description."),
    USER_EXIST("Email exist in database"),
    EMAIL_FORMAT("Invalid email format, example@example.com format required"),
    INSECURE_PASSWORD("Change the password, it is insecure"),
    PHONE_NUMBER_EXIST("One of the phone numbers entered is assigned to another user");

    private final String message;

    ResponseMessageEnum(String message) {
        this.message = message;
    }

}
