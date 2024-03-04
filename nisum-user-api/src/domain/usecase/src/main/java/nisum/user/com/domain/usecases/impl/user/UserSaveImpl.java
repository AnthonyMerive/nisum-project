package nisum.user.com.domain.usecases.impl.user;

import lombok.extern.java.Log;
import nisum.user.com.domain.common.entity.Phone;
import nisum.user.com.domain.common.entity.User;
import nisum.user.com.domain.common.exception.BusinessException;
import nisum.user.com.domain.common.port.UserRepositoryPort;
import nisum.user.com.domain.common.traceability.TraceabilityService;
import nisum.user.com.domain.common.usecase.phone.PhoneFindUseCase;
import nisum.user.com.domain.common.usecase.user.UserSaveUseCase;
import nisum.user.com.domain.common.util.RegexValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Log
@Component
public record UserSaveImpl(
        UserRepositoryPort userRepository,
        PhoneFindUseCase phoneFindUseCase,
        RegexValidator regexValidator,
        TraceabilityService<Object> traceabilityService) implements UserSaveUseCase {

    private static final String USE_CASE_OPERATION = "SAVE_USER";

    private static final String VALIDATE_USER = "VALIDATE_USER";
    private static final String VALIDATE_EMAIL = "VALIDATE_EMAIL";
    private static final String VALIDATE_PASSWORD = "VALIDATE_PASSWORD";
    private static final String VALIDATE_PHONE_NUMBERS = "VALIDATE_PHONE_NUMBERS";

    @Override
    public User save(User user) {

        validateUserToSave(user);
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validatePhoneNumbers(user.getPhones(), user);

        var userSaved = userRepository.save(user);
        traceabilityService.emitTraceabilityOk(userSaved, USE_CASE_OPERATION);

        return userSaved;
    }

    @Override
    public User update(User user) {

        var userToUpdate = userRepository.findById(user.getEmail());

        validateUserToUpdate(userToUpdate, user);
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validatePhoneNumbers(user.getPhones(), user);

        var userUpdated = userRepository.updateUser(user, userToUpdate);
        traceabilityService.emitTraceabilityOk(userUpdated, USE_CASE_OPERATION);

        return userUpdated;
    }

    private void validateUserToSave(User user) {
        if (Objects.nonNull(userRepository.findById(user.getEmail()))) {

            var exception = new BusinessException(BusinessException.Type.USER_EXIST);
            traceabilityService.emitTraceabilityError(user, VALIDATE_USER, exception);
            throw exception;
        }
    }

    private void validateUserToUpdate(User userToUpdate, User userIn) {
        if (Objects.isNull(userToUpdate)) {

            var exception = new BusinessException(BusinessException.Type.USER_NO_EXIST);
            traceabilityService.emitTraceabilityError(userIn, VALIDATE_USER, exception);
            throw exception;
        }
    }

    private void validateEmail(String email) {
        if (regexValidator.isInvalidEmail(email)) {

            var exception = new BusinessException(BusinessException.Type.EMAIL_INVALID);
            traceabilityService.emitTraceabilityError(email, VALIDATE_EMAIL, exception);
            throw exception;
        }
    }

    private void validatePassword(String password) {
        if (regexValidator.isInvalidPassword(password)) {

            var exception = new BusinessException(BusinessException.Type.PASSWORD_INVALID);
            traceabilityService.emitTraceabilityError(password, VALIDATE_PASSWORD, exception);
            throw exception;
        }
    }

    private void validatePhoneNumbers(List<Phone> phones, User user) {
        List<Phone> usedPhones = phones.stream()
                .filter(phone -> phoneFindUseCase.isPhoneNumberUsedByUser(phone.getNumber()))
                .toList();

        if (!usedPhones.isEmpty()) {

            var exception = new BusinessException(BusinessException.Type.PHONE_NUMBER_EXIST);
            traceabilityService.emitTraceabilityError(user, VALIDATE_PHONE_NUMBERS, exception);
            throw exception;
        }
    }
}
