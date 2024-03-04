package nisum.user.com.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import nisum.user.com.domain.common.port.PublisherPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

@Log
@Component
@RequiredArgsConstructor
public class KafkaPublisher implements PublisherPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic.traceability}")
    private String traceabilityTopic;

    public void sendMessage(String message) {

        try {
            kafkaTemplate.send(traceabilityTopic, message).get();
        } catch (InterruptedException | ExecutionException ex) {

            log.log(Level.SEVERE, "DomainEvent send error: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
