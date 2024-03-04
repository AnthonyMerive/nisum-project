package nisum.user.com.domain.common.port;

import nisum.user.com.domain.common.entity.User;

import java.util.List;

public interface UserRepositoryPort {

    User save(User user);

    User findById(String id);

    List<User> findAll();

    void deleteById(String id);

    User updateUser(User userUpdate, User userConsult);

}
