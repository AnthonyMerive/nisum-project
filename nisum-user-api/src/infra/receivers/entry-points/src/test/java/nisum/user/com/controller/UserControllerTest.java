package nisum.user.com.controller;

import nisum.user.com.controller.auth.JWTValidator;
import nisum.user.com.controller.dto.UserRequestDTO;
import nisum.user.com.controller.dto.UserResponseDTO;
import nisum.user.com.controller.util.ResponseBuilder;
import nisum.user.com.domain.common.entity.User;
import nisum.user.com.domain.common.exception.BusinessException;
import nisum.user.com.domain.usecases.UserUseCases;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class UserControllerTest implements ResponseBuilder {

    private final UserUseCases userUseCase = Mockito.mock(UserUseCases.class);
    private final JWTValidator jWTValidator = Mockito.mock(JWTValidator.class);

    private final UserController controller = new UserController(userUseCase, jWTValidator);

    private final static String TEST = "TEST";

    @Test
    void saveUserTestWhenResponse201() {
        var expect = build201Response(
                UserResponseDTO.builder()
                        .email(TEST)
                        .build());

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.findById(anyString())).thenReturn(null);
        Mockito.when(userUseCase.save(any(User.class))).thenReturn(User.builder().email(TEST).phones(List.of()).build());

        var result = controller.saveUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(201));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getData().getEmail(), Objects.requireNonNull(expect.getBody()).getData().getEmail());
    }

    @Test
    void saveUserTestWhenResponseEmailExist() {
        var expect = "Email exist in database";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.save(any())).thenThrow(new BusinessException(BusinessException.Type.USER_EXIST));

        var result = controller.saveUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(400));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void saveUserTestWhenResponseBadRequestEmail() {
        var expect = "Invalid email format, example@example.com format required";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.save(any())).thenThrow(new BusinessException(BusinessException.Type.EMAIL_INVALID));

        var result = controller.saveUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(400));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void saveUserTestWhenResponseBadRequestPassword() {
        var expect = "Change the password, it is insecure";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.save(any())).thenThrow(new BusinessException(BusinessException.Type.PASSWORD_INVALID));

        var result = controller.saveUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(400));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void saveUserTestUnauthorized() {
        var expect = "Invalid Token";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(false);

        var result = controller.saveUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(401));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void findByEmailTest() {
        var expect = build200Response(
                UserRequestDTO.builder()
                        .email(TEST)
                        .password(TEST)
                        .phones(List.of())
                        .build(),TEST);

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.findById(anyString())).thenReturn(User.builder().password(TEST).email(TEST).phones(List.of()).build());

        var result = controller.findByEmail(request.getEmail(), TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getData().getEmail(), Objects.requireNonNull(expect.getBody()).getData().getEmail());

    }

    @Test
    void findByEmailTestUnauthorized() {
        var expect = "Invalid Token";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(false);

        var result = controller.findByEmail(request.getEmail(), TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(401));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void findAllUsersTest() {
        var expect = 1;

        User user = User.builder().email(TEST).phones(List.of()).build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.findAll()).thenReturn(List.of(user));

        var result = controller.findAllUsers(TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getData().size(), expect);

    }

    @Test
    void findAllUsersTestUnauthorized() {
        var expect = "Invalid Token";

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(false);

        var result = controller.findAllUsers(TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(401));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void updateUserTest() {
        var expect = build200Response(
                UserResponseDTO.builder()
                        .email(TEST)
                        .build(), TEST);

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.update(any(User.class))).thenReturn(User.builder().email(TEST).phones(List.of()).build());

        var result = controller.updateUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getData().getEmail(), Objects.requireNonNull(expect.getBody()).getData().getEmail());
    }

    @Test
    void updateUserTestUnauthorized() {
        var expect = "Invalid Token";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(false);

        var result = controller.updateUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(401));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void updateUserTestWhenResponseBadRequestEmail() {
        var expect = "Invalid email format, example@example.com format required";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.update(any())).thenThrow(new BusinessException(BusinessException.Type.EMAIL_INVALID));

        var result = controller.updateUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(400));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void updateUserTestWhenResponseBadRequestPassword() {
        var expect = "Change the password, it is insecure";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .password(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.update(any())).thenThrow(new BusinessException(BusinessException.Type.PASSWORD_INVALID));

        var result = controller.updateUser(request, TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(400));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }

    @Test
    void deleteByEmailTest() {
        var expect = build200Response(
                UserResponseDTO.builder()
                        .email(TEST)
                        .phones(List.of())
                        .build(), TEST);

        var request = UserRequestDTO.builder()
                .email(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.deleteById(anyString())).thenReturn(User.builder().email(TEST).phones(List.of()).build());

        var result = controller.deleteByEmail(request.getEmail(), TEST);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getData().getEmail(), Objects.requireNonNull(expect.getBody()).getData().getEmail());

    }

    @Test
    void deleteByEmailTestUnauthorized() {
        var expect = "Invalid Token";

        var request = UserRequestDTO.builder()
                .email(TEST)
                .phones(List.of())
                .build();

        Mockito.when(jWTValidator.isValidToken(anyString())).thenReturn(false);

        var result = controller.deleteByEmail(request.getEmail(), "test");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(401));
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getMessage(), expect);
    }
}