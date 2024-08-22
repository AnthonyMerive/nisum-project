package nisum.user.com.controller.util;

import nisum.user.com.controller.dto.ResponseDTO;
import nisum.user.com.controller.dto.UserResponseDTO;
import nisum.user.com.domain.common.exception.BusinessExceptionEnum;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static nisum.user.com.controller.util.ResponseMessageEnum.*;

public interface ResponseHandler extends ResponseBuilder {

    default ResponseEntity<ResponseDTO<UserResponseDTO>> handleError(String errorMessage) {

        Map<String, ResponseEntity<ResponseDTO<UserResponseDTO>>> errors = Map.ofEntries(
                Map.entry(BusinessExceptionEnum.USER_EXIST.getMessage(), build400Response(USER_EXIST.getMessage())),
                Map.entry(BusinessExceptionEnum.EMAIL_INVALID.getMessage(), build400Response(EMAIL_FORMAT.getMessage())),
                Map.entry(BusinessExceptionEnum.PASSWORD_INVALID.getMessage(), build400Response(INSECURE_PASSWORD.getMessage())),
                Map.entry(BusinessExceptionEnum.PHONE_NUMBER_EXIST.getMessage(), build400Response(PHONE_NUMBER_EXIST.getMessage())),
                Map.entry(BusinessExceptionEnum.USER_NO_EXIST.getMessage(), build404Response())
        );

        return errors.getOrDefault(errorMessage, build500Response(errorMessage));
    }
}
