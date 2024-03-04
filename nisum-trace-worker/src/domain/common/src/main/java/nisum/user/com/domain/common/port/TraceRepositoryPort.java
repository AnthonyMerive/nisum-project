package nisum.user.com.domain.common.port;

import nisum.user.com.domain.common.entity.TraceMessage;

public interface TraceRepositoryPort {

    TraceMessage save(TraceMessage traceMessage);
}
