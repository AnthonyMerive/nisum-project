package nisum.user.com.repository.traceability;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nisum.user.com.domain.common.entity.TraceMessage;
import nisum.user.com.domain.common.port.TraceRepositoryPort;
import nisum.user.com.repository.mapper.TraceMessageDataMapper;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TraceMessageRepositoryAdapter implements TraceRepositoryPort, TraceMessageDataMapper {

    private final TraceMessageRepository repository;

    @Override
    @Transactional
    public TraceMessage save(TraceMessage traceMessage) {

        var traceSaved = repository.save(mapTraceMessageEntityToData(traceMessage));
        return mapTraceDataToEntity(traceSaved);
    }
}
