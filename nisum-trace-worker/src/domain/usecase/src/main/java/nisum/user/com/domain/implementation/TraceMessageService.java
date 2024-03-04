package nisum.user.com.domain.implementation;

import lombok.extern.java.Log;
import nisum.user.com.domain.common.entity.TraceMessage;
import nisum.user.com.domain.common.exception.BusinessException;
import nisum.user.com.domain.common.port.TraceRepositoryPort;

import java.util.logging.Level;

@Log
public record TraceMessageService(TraceRepositoryPort traceRepositoryPort) {

    public TraceMessage save(TraceMessage traceMessage) {
        try {
            return traceRepositoryPort.save(traceMessage);
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Error: " + ex.getMessage());
            throw new BusinessException(BusinessException.Type.TRACE_SAVE_ERROR);
        }
    }
}
