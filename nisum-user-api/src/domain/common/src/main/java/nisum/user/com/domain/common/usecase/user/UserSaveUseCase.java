package nisum.user.com.domain.common.usecase.user;

import nisum.user.com.domain.common.entity.User;

public interface UserSaveUseCase {

    User save(User user);
    User update(User user);
}
