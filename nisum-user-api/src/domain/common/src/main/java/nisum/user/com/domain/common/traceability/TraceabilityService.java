package nisum.user.com.domain.common.traceability;

public interface TraceabilityService<T> {

    void emitTraceabilityOk(T data, String operation);
    void emitTraceabilityError(T data, String operation, Exception exception);
}
