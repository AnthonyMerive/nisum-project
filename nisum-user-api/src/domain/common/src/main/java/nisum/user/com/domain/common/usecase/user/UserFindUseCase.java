package nisum.user.com.domain.common.usecase.user;

import nisum.user.com.domain.common.entity.User;

import java.util.List;

public interface UserFindUseCase {

    User findById(String id);
    List<User> findAll();
}
