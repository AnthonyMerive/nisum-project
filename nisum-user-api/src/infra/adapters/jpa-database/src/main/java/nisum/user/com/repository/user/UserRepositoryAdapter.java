package nisum.user.com.repository.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nisum.user.com.domain.common.entity.User;
import nisum.user.com.domain.common.port.UserRepositoryPort;
import nisum.user.com.repository.mapper.UserRepositoryMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository repository;

    @Override
    @Transactional
    public User save(User user) {
        var userSaved = repository.save(UserRepositoryMapper.mapUserEntityToCreateData(user));
        return UserRepositoryMapper.mapDataToUserEntity(userSaved);
    }

    @Override
    public User findById(String id) {
        var userOptional = repository.findById(id);

        return userOptional.map(UserRepositoryMapper::mapDataToUserEntity).orElse(null);
    }

    @Override
    public List<User> findAll() {
        var usersData = repository.findAll();
        return usersData.stream().map(UserRepositoryMapper::mapDataToUserEntity).toList();
    }

    @Override
    public void deleteById(String id) {
        var userOptional = repository.findById(id);

        if (userOptional.isPresent()) repository.deleteById(id);
    }

    @Override
    public User updateUser(User userUpdate, User userExist){

        var userSaved = repository.save(UserRepositoryMapper.mapUserEntityToUpdateData(userUpdate, userExist));
        return UserRepositoryMapper.mapDataToUserEntity(userSaved);
    }
}
