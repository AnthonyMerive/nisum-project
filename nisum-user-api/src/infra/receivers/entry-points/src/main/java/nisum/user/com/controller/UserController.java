package nisum.user.com.controller;

import nisum.user.com.controller.auth.JWTValidator;
import nisum.user.com.controller.dto.ResponseDTO;
import nisum.user.com.controller.dto.UserRequestDTO;
import nisum.user.com.controller.dto.UserResponseDTO;
import nisum.user.com.controller.mapper.UserMapper;
import nisum.user.com.controller.util.ResponseBuilder;
import nisum.user.com.domain.usecases.UserUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user/api/v1")
public record UserController(UserUseCases userUseCases,
                             JWTValidator jwtValidator) implements ResponseBuilder {

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> saveUser(
            @RequestBody UserRequestDTO userDTO,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader))
                return build401Response();

            var userSaved = userUseCases.save(UserMapper.mapDtoToUser(userDTO));
            return build201Response(UserMapper.mapUserToDTO(userSaved));

        } catch (Exception ex) { return handleErrorResponse(ex.getMessage()); }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> findByEmail(
            @RequestHeader("Email") String email,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader))
                return build401Response();

            var userConsulted = userUseCases.findById(email);
            return build200Response(UserMapper.mapUserToDTO(userConsulted), "Success get");

        } catch (Exception ex) { return handleErrorResponse(ex.getMessage()); }
    }

    @GetMapping("/search-all")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> findAllUsers(
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader))
                return build401Response();

            var usersConsulted = userUseCases.findAll();
            var usersDtoConsulted = usersConsulted.stream().map(UserMapper::mapUserToDTO).toList();

            return build200Response(usersDtoConsulted, "Success get");

        } catch (Exception ex) { return build500Response(ex.getMessage()); }
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(
            @RequestBody UserRequestDTO userDTO,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader))
                return build401Response();

            var userUpdated = userUseCases.update(UserMapper.mapDtoToUser(userDTO));
            return build200Response(UserMapper.mapUserToDTO(userUpdated), "Success update");

        } catch (Exception ex) { return handleErrorResponse(ex.getMessage()); }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> deleteByEmail(
            @RequestHeader("Email") String email,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {
            if (!jwtValidator.isValidToken(authorizationHeader))
                return build401Response();

            var userDeleted = userUseCases.deleteById(email);
            return build200Response(UserMapper.mapUserToDTO(userDeleted), "Success delete");

        } catch (Exception ex) { return handleErrorResponse(ex.getMessage()); }
    }

    private ResponseEntity<ResponseDTO<UserResponseDTO>> handleErrorResponse(String errorMessage) {

        var userExistMessage = "Email exist in database";
        var emailFormatMessage = "Invalid email format, example@example.com format required";
        var insecurePasswordMessage = "Change the password, it is insecure";
        var phoneNumberExistMessage = "one of the phone numbers entered is assigned to another user";

        return switch (errorMessage) {
            case "USER EXIST IN DATABASE" -> build400Response(userExistMessage);
            case "THE EMAIL FORMAT IS INVALID" -> build400Response(emailFormatMessage);
            case "INSECURE PASSWORD" -> build400Response(insecurePasswordMessage);
            case "THE PHONE NUMBER EXIST IN OTHER USER" -> build400Response(phoneNumberExistMessage);
            case "USER NO EXIST IN DATABASE" -> build404Response();
            default -> build500Response(errorMessage);
        };
    }
}