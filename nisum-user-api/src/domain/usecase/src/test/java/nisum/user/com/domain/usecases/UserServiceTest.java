package nisum.user.com.domain.usecases;

import nisum.user.com.domain.common.entity.User;
import nisum.user.com.domain.common.usecase.user.UserDeleteUseCase;
import nisum.user.com.domain.common.usecase.user.UserFindUseCase;
import nisum.user.com.domain.common.usecase.user.UserSaveUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class UserServiceTest {

    private final UserDeleteUseCase userDeleteUseCase = Mockito.mock(UserDeleteUseCase.class);
    private final UserFindUseCase userFindUseCase = Mockito.mock(UserFindUseCase.class);
    private final UserSaveUseCase userSaveUseCase = Mockito.mock(UserSaveUseCase.class);
    private final UserUseCases service = new UserUseCases(userDeleteUseCase, userFindUseCase, userSaveUseCase);

    @Test
    void saveTest() {
        var expect = "TEST";

        Mockito.when(userSaveUseCase.save(any(User.class))).thenReturn(User.builder().id(expect).build());
        var result = service.save(User.builder().build());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expect, result.getId());
    }

    @Test
    void getByEmailTest() {
        var expect = "example@example.com";

        Mockito.when(userFindUseCase.findById(anyString())).thenReturn(User.builder().email(expect).build());

        var result = service.findById("TEST");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expect, result.getEmail());
    }

    @Test
    void getAllTest() {
        var expect = 2;
        var expectedId1 = "1";
        var expectedId2 = "2";

        Mockito.when(userFindUseCase.findAll()).thenReturn(List.of(User.builder().id("1").build(), User.builder().id("2").build()));

        var result = service.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expect, result.size());
        Assertions.assertEquals(expectedId1, result.get(0).getId());
        Assertions.assertEquals(expectedId2, result.get(1).getId());
    }

    @Test
    void updateUserTestWhenUserExist() {

        var expect = "test@test.com";

        Mockito.when(userSaveUseCase.update(any(User.class))).thenReturn(User.builder().email(expect).build());

        var result = service.update(User.builder().email(expect).build());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expect, result.getEmail());
    }

    @Test
    void deleteByEmailTest() {
        var expect = "example@example.com";

        Mockito.when(userDeleteUseCase.deleteById(anyString())).thenReturn(User.builder().email(expect).build());

        var result = service.deleteById(anyString());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expect, result.getEmail());
    }
}