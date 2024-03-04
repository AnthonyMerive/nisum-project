package nisum.user.com.configuration;

import nisum.user.com.domain.common.port.PublisherPort;
import nisum.user.com.domain.common.traceability.TraceabilityService;
import nisum.user.com.domain.usecases.impl.traceability.TraceabilityImpl;
import nisum.user.com.publisher.KafkaPublisher;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class TraceabilityConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.groupId}")
    private String groupId;

    @Bean
    public ProducerFactory<String, String> producer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producer());
    }

    @Bean
    public PublisherPort publisherPort(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaPublisher(kafkaTemplate);
    }

    @Bean
    public TraceabilityService<Object> traceabilityService(PublisherPort publisherPort) {
        return new TraceabilityImpl(publisherPort);
    }
}
