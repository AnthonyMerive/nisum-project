package nisum.user.com.domain.common.exception;

public class BusinessException extends ApplicationException {

    public enum Type {
        TRACE_SAVE_ERROR("ERROR SAVE THE TRACEABILITY MESSAGE")
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
