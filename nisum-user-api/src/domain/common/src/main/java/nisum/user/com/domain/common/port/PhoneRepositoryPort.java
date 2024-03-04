package nisum.user.com.domain.common.port;

public interface PhoneRepositoryPort {

    Boolean isPhoneNumberUsedByUser(String phoneNumber);
}
