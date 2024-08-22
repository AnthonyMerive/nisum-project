package nisum.user.com.domain.common.exception;

public class BusinessException extends ApplicationException {

    public enum Type {
        USER_EXIST(BusinessExceptionEnum.USER_EXIST.getMessage()),
        EMAIL_INVALID(BusinessExceptionEnum.EMAIL_INVALID.getMessage()),
        PASSWORD_INVALID(BusinessExceptionEnum.PASSWORD_INVALID.getMessage()),
        USER_NO_EXIST(BusinessExceptionEnum.USER_NO_EXIST.getMessage()),
        PHONE_NUMBER_EXIST(BusinessExceptionEnum.PHONE_NUMBER_EXIST.getMessage())
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
