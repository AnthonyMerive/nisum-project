package nisum.user.com.domain.common.exception;

public class BusinessException extends ApplicationException {

    public enum Type {
        USER_EXIST("USER EXIST IN DATABASE"),
        EMAIL_INVALID("THE EMAIL FORMAT IS INVALID"),
        PASSWORD_INVALID("INSECURE PASSWORD"),
        USER_NO_EXIST("USER NO EXIST IN DATABASE"),
        PHONE_NUMBER_EXIST("THE PHONE NUMBER EXIST IN OTHER USER")
        ;

        private final String message;

        public BusinessException build() {

            return new BusinessException(this);
        }

        Type(String message) {
            this.message = message;
        }

    }

    private final Type type;

    public BusinessException(Type type) {
        super(type.message);
        this.type = type;
    }

    @Override
    public String getCode() {
        return type.name();
    }


}
