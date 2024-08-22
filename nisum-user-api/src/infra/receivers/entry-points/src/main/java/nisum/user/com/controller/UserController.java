package nisum.user.com.controller;

import nisum.user.com.controller.auth.JWTValidator;
import nisum.user.com.controller.dto.ResponseDTO;
import nisum.user.com.controller.dto.UserRequestDTO;
import nisum.user.com.controller.dto.UserResponseDTO;
import nisum.user.com.controller.mapper.UserMapper;
import nisum.user.com.controller.util.ResponseHandler;
import nisum.user.com.domain.usecases.UserUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static nisum.user.com.controller.util.ResponseMessageEnum.SUCCESS;

@RestController
@RequestMapping(value = "user/api/v1")
public record UserController(UserUseCases userUseCases,
                             JWTValidator jwtValidator) implements ResponseHandler {

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> saveUser(
            @RequestBody UserRequestDTO userDTO,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader)) return build401Response();

            var userSaved = userUseCases.save(UserMapper.mapDtoToUser(userDTO));
            return build201Response(UserMapper.mapUserToDTO(userSaved));

        } catch (Exception ex) { return handleError(ex.getMessage()); }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> findByEmail(
            @RequestHeader("Email") String email,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader)) return build401Response();

            var userConsulted = userUseCases.findById(email);
            return build200Response(UserMapper.mapUserToDTO(userConsulted), SUCCESS.getMessage());

        } catch (Exception ex) { return handleError(ex.getMessage()); }
    }

    @GetMapping("/search-all")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> findAllUsers(
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader)) return build401Response();

            var usersConsulted = userUseCases.findAll();
            var usersDtoConsulted = usersConsulted.stream().map(UserMapper::mapUserToDTO).toList();

            return build200Response(usersDtoConsulted, SUCCESS.getMessage());

        } catch (Exception ex) { return build500Response(ex.getMessage()); }
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(
            @RequestBody UserRequestDTO userDTO,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader)) return build401Response();

            var userUpdated = userUseCases.update(UserMapper.mapDtoToUser(userDTO));
            return build200Response(UserMapper.mapUserToDTO(userUpdated), SUCCESS.getMessage());

        } catch (Exception ex) { return handleError(ex.getMessage()); }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> deleteByEmail(
            @RequestHeader("Email") String email,
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (!jwtValidator.isValidToken(authorizationHeader)) return build401Response();

            var userDeleted = userUseCases.deleteById(email);
            return build200Response(UserMapper.mapUserToDTO(userDeleted), SUCCESS.getMessage());

        } catch (Exception ex) { return handleError(ex.getMessage()); }
    }
}