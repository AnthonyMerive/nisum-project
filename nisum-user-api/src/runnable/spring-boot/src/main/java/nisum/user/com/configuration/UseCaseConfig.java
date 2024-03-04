package nisum.user.com.configuration;

import nisum.user.com.domain.common.port.PhoneRepositoryPort;
import nisum.user.com.domain.common.port.UserRepositoryPort;
import nisum.user.com.domain.common.traceability.TraceabilityService;
import nisum.user.com.domain.common.usecase.phone.PhoneFindUseCase;
import nisum.user.com.domain.common.usecase.user.UserDeleteUseCase;
import nisum.user.com.domain.common.usecase.user.UserFindUseCase;
import nisum.user.com.domain.common.usecase.user.UserSaveUseCase;
import nisum.user.com.domain.common.util.RegexValidator;
import nisum.user.com.domain.usecases.UserUseCases;
import nisum.user.com.domain.usecases.impl.phone.PhoneFindImpl;
import nisum.user.com.domain.usecases.impl.user.UserDeleteImpl;
import nisum.user.com.domain.usecases.impl.user.UserFindImpl;
import nisum.user.com.domain.usecases.impl.user.UserSaveImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PhoneFindUseCase phoneFindUseCase(PhoneRepositoryPort phoneRepositoryPort) {
        return new PhoneFindImpl(phoneRepositoryPort);
    }

    @Bean
    public UserDeleteUseCase userDeleteUseCase(
            UserRepositoryPort userRepositoryPort,
            TraceabilityService<Object> traceabilityService) {
        return new UserDeleteImpl(userRepositoryPort, traceabilityService);
    }

    @Bean
    public UserFindUseCase userFindUseCase(
            UserRepositoryPort userRepositoryPort,
            TraceabilityService<Object> traceabilityService) {
        return new UserFindImpl(userRepositoryPort,traceabilityService);
    }

    @Bean
    public UserSaveUseCase userSaveUseCase(
            UserRepositoryPort userRepositoryPort,
            PhoneFindUseCase phoneFindUseCase,
            RegexValidator regexValidator,
            TraceabilityService<Object> traceabilityService) {
        return new UserSaveImpl(userRepositoryPort, phoneFindUseCase, regexValidator, traceabilityService);
    }

    @Bean
    public UserUseCases userUseCases(
            UserDeleteUseCase userDeleteUseCase,
            UserFindUseCase userFindUseCase,
            UserSaveUseCase userSaveUseCase
    ) {
        return new UserUseCases(userDeleteUseCase, userFindUseCase, userSaveUseCase);
    }
}
