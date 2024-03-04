package nisum.user.com.receivers;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import nisum.user.com.domain.common.entity.TraceMessage;
import nisum.user.com.domain.implementation.TraceMessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Log
@Component
public record KafkaConsumer(TraceMessageService service) {

    @KafkaListener(topics = "${kafka.topic.traceability}", containerFactory = "containerFactory")
    public void listener(String message) {

        log.log(Level.INFO, "Domain Event received...");

        var gson = new Gson();
        var traceMessage = gson.fromJson(message, TraceMessage.class);

        var traceMessageSaved = service.save(traceMessage);

        log.log(Level.INFO, "Domain Event saved: " + traceMessageSaved.toString());
    }


}
