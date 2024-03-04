package nisum.user.com.domain.usecases.impl.phone;

import lombok.extern.java.Log;
import nisum.user.com.domain.common.port.PhoneRepositoryPort;
import nisum.user.com.domain.common.usecase.phone.PhoneFindUseCase;
import org.springframework.stereotype.Component;

@Log
@Component
public record PhoneFindImpl(PhoneRepositoryPort phoneRepository) implements PhoneFindUseCase {

    @Override
    public Boolean isPhoneNumberUsedByUser(String phoneNumber) {
        return phoneRepository.isPhoneNumberUsedByUser(phoneNumber);
    }
}
