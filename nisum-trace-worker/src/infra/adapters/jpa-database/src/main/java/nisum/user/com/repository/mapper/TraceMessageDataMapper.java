package nisum.user.com.repository.mapper;

import nisum.user.com.domain.common.entity.TraceMessage;
import nisum.user.com.repository.model.TraceMessageData;

import java.util.Objects;

public interface TraceMessageDataMapper {

    default TraceMessageData mapTraceMessageEntityToData(TraceMessage traceMessage) {
        return TraceMessageData.builder()
                .transactionId(traceMessage.getTransactionId())
                .operation(traceMessage.getOperation())
                .errorTrace(Objects.isNull(traceMessage.getErrorTrace()) ? "" : traceMessage.getErrorTrace())
                .status(traceMessage.getStatus())
                .data(traceMessage.getData())
                .build();
    }

    default TraceMessage mapTraceDataToEntity(TraceMessageData traceMessage) {
        return TraceMessage.builder()
                .transactionId(traceMessage.getTransactionId())
                .operation(traceMessage.getOperation())
                .errorTrace(traceMessage.getErrorTrace())
                .status(traceMessage.getStatus())
                .data(traceMessage.getData())
                .build();
    }


}
