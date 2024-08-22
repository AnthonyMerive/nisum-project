package nisum.user.com.controller.util;

import nisum.user.com.controller.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

import static nisum.user.com.controller.util.ResponseMessageEnum.*;

public interface ResponseBuilder {

    default <T> ResponseEntity<ResponseDTO<T>> build200Response(T data, String message) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(message)
                        .code(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build201Response(T data) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body((
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(SUCCESS.getMessage())
                        .code(HttpStatus.CREATED.value())
                        .data(data)
                        .build()));
    }

    default <T> ResponseEntity<ResponseDTO<T>> build404Response() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(USER_NOT_FOUND.getMessage())
                        .code(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build400Response(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(message)
                        .code(HttpStatus.BAD_REQUEST.value())
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build401Response() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(INVALID_TOKEN.getMessage())
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build500Response(String exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(UNHANDLED_EXCEPTION.getMessage()
                                .concat(Objects.requireNonNullElse(
                                        exception,
                                        NO_ERROR_DESCRIPTION.getMessage())))
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build());
    }
}
