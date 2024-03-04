package nisum.user.com.repository.phone;

import nisum.user.com.repository.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneData, String> {
}
