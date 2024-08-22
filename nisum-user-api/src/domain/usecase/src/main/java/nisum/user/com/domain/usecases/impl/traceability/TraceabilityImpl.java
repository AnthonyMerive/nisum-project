package nisum.user.com.domain.usecases.impl.traceability;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import nisum.user.com.domain.common.port.PublisherPort;
import nisum.user.com.domain.common.traceability.TraceabilityService;
import nisum.user.com.domain.usecases.impl.traceability.util.MessageBuilder;

import java.util.logging.Level;

@Log
public record TraceabilityImpl(PublisherPort publisher, Boolean traceabilityEnable)
        implements TraceabilityService<Object>, MessageBuilder<String> {

    @Override
    public void emitTraceabilityOk(Object data, String operation) {

        var gson = new Gson();
        var serializedData = gson.toJson(data);
        var successMessage = buildSuccessMessage(serializedData, operation);
        var message = gson.toJson(successMessage);

        if(Boolean.TRUE.equals(traceabilityEnable)) publisher.sendMessage(message);

        log.log(Level.INFO, "DomainEvent emitted: " + message);
    }

    @Override
    public void emitTraceabilityError(Object data, String operation, Exception exception) {

        var gson = new Gson();
        var serializedData = gson.toJson(data);
        var errorMessage = buildErrorMessage(serializedData, operation, exception);
        var message = gson.toJson(errorMessage);

        if(Boolean.TRUE.equals(traceabilityEnable)) publisher.sendMessage(message);

        log.log(Level.SEVERE, "DomainEvent emitted: " + message);
    }
}
