package nisum.user.com.domain.usecases;

import lombok.extern.java.Log;
import nisum.user.com.domain.common.entity.User;
import nisum.user.com.domain.common.usecase.user.UserDeleteUseCase;
import nisum.user.com.domain.common.usecase.user.UserFindUseCase;
import nisum.user.com.domain.common.usecase.user.UserSaveUseCase;

import java.util.List;

@Log
public record UserUseCases(UserDeleteUseCase userDeleteUseCase,
                           UserFindUseCase userFindUseCase,
                           UserSaveUseCase userSaveUseCase) {

    public User save(User user) {
        return userSaveUseCase.save(user);
    }

    public User findById(String id) {
        return userFindUseCase.findById(id);
    }

    public List<User> findAll() {
        return userFindUseCase.findAll();
    }

    public User update(User user) {
        return userSaveUseCase.update(user);
    }

    public User deleteById(String id) {
        return userDeleteUseCase.deleteById(id);
    }
}
