package nisum.user.com.domain.usecases.impl.traceability.util;

import nisum.user.com.domain.common.traceability.model.Message;

import java.util.UUID;

public interface MessageBuilder<T> {

    default Message<T> buildSuccessMessage(T data, String operation){
        return Message.<T>builder()
                .transactionId(UUID.randomUUID().toString())
                .operation(operation)
                .status("OK")
                .errorTrace(null)
                .data(data)
                .build();
    }

    default Message<T> buildErrorMessage(T data, String operation, Exception exception){
        return Message.<T>builder()
                .transactionId(UUID.randomUUID().toString())
                .operation(operation)
                .status("ERROR")
                .errorTrace(exception.getMessage())
                .data(data)
                .build();
    }
}
