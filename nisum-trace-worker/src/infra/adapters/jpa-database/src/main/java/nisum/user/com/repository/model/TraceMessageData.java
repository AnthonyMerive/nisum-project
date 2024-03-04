package nisum.user.com.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "domain_events")
@NoArgsConstructor
@AllArgsConstructor
public class TraceMessageData {

    @Id
    private String transactionId;

    private String operation;

    private String errorTrace;

    private String status;

    @Column(length = 1500)
    private String data;

}
