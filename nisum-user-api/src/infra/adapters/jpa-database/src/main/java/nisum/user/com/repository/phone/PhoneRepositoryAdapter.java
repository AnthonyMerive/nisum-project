package nisum.user.com.repository.phone;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nisum.user.com.domain.common.port.PhoneRepositoryPort;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PhoneRepositoryAdapter implements PhoneRepositoryPort {

    private final PhoneRepository phoneRepository;

    @Override
    @Transactional
    public Boolean isPhoneNumberUsedByUser(String phoneNumber) {

        return phoneRepository.findById(phoneNumber).isPresent();
    }
}
