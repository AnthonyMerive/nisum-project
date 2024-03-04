package nisum.user.com.domain.usecases.impl.user;

import lombok.extern.java.Log;
import nisum.user.com.domain.common.entity.User;
import nisum.user.com.domain.common.exception.BusinessException;
import nisum.user.com.domain.common.port.UserRepositoryPort;
import nisum.user.com.domain.common.traceability.TraceabilityService;
import nisum.user.com.domain.common.usecase.user.UserDeleteUseCase;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Log
@Component
public record UserDeleteImpl(
        UserRepositoryPort userRepository,
        TraceabilityService<Object> traceabilityService) implements UserDeleteUseCase {

    private static final String USE_CASE_OPERATION = "DELETE_USER";

    private static final String VALIDATE_USER = "VALIDATE_USER";

    @Override
    public User deleteById(String id) {

        var user = userRepository.findById(id);

        validateUserToDelete(user, id);

        userRepository.deleteById(id);

        traceabilityService.emitTraceabilityOk(user, USE_CASE_OPERATION);
        return user;
    }

    private void validateUserToDelete(User user, String id) {

        if (Objects.isNull(user)) {

            var exception = new BusinessException(BusinessException.Type.USER_NO_EXIST);
            traceabilityService.emitTraceabilityError(id, VALIDATE_USER, exception);
            throw exception;
        }
    }
}
