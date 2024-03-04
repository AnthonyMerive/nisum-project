package nisum.user.com.controller.util;

import nisum.user.com.controller.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ResponseBuilder {

    default <T> ResponseEntity<ResponseDTO<T>> build200Response(T data, String message) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(message)
                        .code(200)
                        .data(data)
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build201Response(T data) {
        return ResponseEntity.ok(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message("Success creation")
                        .code(201)
                        .data(data)
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build404Response() {
        return ResponseEntity.status(404).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message("User not found")
                        .code(404)
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build400Response(String message) {
        return ResponseEntity.status(400).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message(message)
                        .code(400)
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build401Response() {
        return ResponseEntity.status(401).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message("Invalid Token")
                        .code(401)
                        .build());
    }

    default <T> ResponseEntity<ResponseDTO<T>> build500Response(String exception) {
        return ResponseEntity.status(500).body(
                ResponseDTO.<T>builder()
                        .transactionId(UUID.randomUUID().toString())
                        .message("Unhandled exception: " + exception)
                        .code(500)
                        .build());
    }
}
