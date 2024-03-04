package nisum.user.com.domain.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TraceMessage {

    String transactionId;
    String operation;
    String errorTrace;
    String status;
    String data;
}
