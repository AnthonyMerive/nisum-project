package nisum.user.com.domain.common.usecase.user;

import nisum.user.com.domain.common.entity.User;

public interface UserDeleteUseCase {

    User deleteById(String id);
}
