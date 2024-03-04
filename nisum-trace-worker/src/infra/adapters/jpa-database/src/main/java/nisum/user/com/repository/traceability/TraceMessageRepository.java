package nisum.user.com.repository.traceability;

import nisum.user.com.repository.model.TraceMessageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceMessageRepository extends JpaRepository<TraceMessageData, String> {
}
