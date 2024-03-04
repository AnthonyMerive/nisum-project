package nisum.user.com.domain.common.traceability.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {

    String transactionId;
    String operation;
    String errorTrace;
    String status;
    T data;
}
