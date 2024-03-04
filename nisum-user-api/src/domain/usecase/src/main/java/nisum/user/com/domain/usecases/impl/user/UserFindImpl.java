package nisum.user.com.domain.usecases.impl.user;

import lombok.extern.java.Log;
import nisum.user.com.domain.common.entity.User;
import nisum.user.com.domain.common.exception.BusinessException;
import nisum.user.com.domain.common.port.UserRepositoryPort;
import nisum.user.com.domain.common.traceability.TraceabilityService;
import nisum.user.com.domain.common.usecase.user.UserFindUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Log
@Component
public record UserFindImpl(
        UserRepositoryPort userRepository,
        TraceabilityService<Object> traceabilityService) implements UserFindUseCase {

    private static final String USE_CASE_OPERATION = "FIND_USER";

    private static final String VALIDATE_USER = "VALIDATE_USER";

    @Override
    public User findById(String id) {

        var userConsulted = userRepository.findById(id);

        validateUserExist(userConsulted, id);
        traceabilityService.emitTraceabilityOk(userConsulted, USE_CASE_OPERATION);

        return userConsulted;

    }

    @Override
    public List<User> findAll() {

        var users = userRepository.findAll();
        traceabilityService.emitTraceabilityOk("Get users quantity: " + users.size(), USE_CASE_OPERATION);

        return users;
    }


    private void validateUserExist(User user, String id) {

        if (Objects.isNull(user)) {

            var exception = new BusinessException(BusinessException.Type.USER_NO_EXIST);
            traceabilityService.emitTraceabilityError(id, VALIDATE_USER, exception);
            throw exception;
        }
    }
}
