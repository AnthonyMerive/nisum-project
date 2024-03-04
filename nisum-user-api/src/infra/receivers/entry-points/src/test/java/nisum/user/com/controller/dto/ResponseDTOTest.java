package nisum.user.com.controller.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ResponseDTOTest {

    private static final Date TEST_DATE = new Date();

    PhoneDTO phoneDTO = PhoneDTO.builder()
            .number("3104206988")
            .cityCode("1")
            .countryCode("57")
            .build();

    PhoneDTO phoneDtoTwo = PhoneDTO.builder()
            .number("3113659874")
            .cityCode("2")
            .countryCode("43")
            .build();

    List<PhoneDTO> phonesDTO = List.of(phoneDTO, phoneDtoTwo);

    UserResponseDTO userResponseDTO = UserResponseDTO.builder()
            .id("ej345ea0-5636-11ee-8c99-0242ac104581")
            .name("Miguel Correa")
            .email("miguel@correa.org")
            .password("Hunter123")
            .phones(phonesDTO)
            .created(TEST_DATE)
            .modified(TEST_DATE)
            .lastLogin(TEST_DATE)
            .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bS11c2VyIiwiZXhwIjoxNjk1MDU2NjI2fQ.hpAV4met-ZyOtwrBVFO9xGHFt8-VlModnYoP4_uojtk")
            .isActive(true)
            .build();

    ResponseDTO responseDTO = ResponseDTO.builder()
            .transactionId("ej345ea0-5636-11ee-8c99-0242ac104581")
            .code(200)
            .message("Exitoso")
            .error(null)
            .data(userResponseDTO)
            .build();

    @Test
    void testEmptyInstance() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(500);

        assertEquals(500, responseDTO.getCode());
    }

    @Test
    void testGetter() {
        assertEquals(userResponseDTO, responseDTO.getData());
        assertEquals(200, responseDTO.getCode());
    }

    @Test
    void testEquals() {
        ResponseDTO responseDtoTwo = ResponseDTO.builder()
                .transactionId("ej345ea0-5636-11ee-8c99-0242ac104581")
                .code(200)
                .message("Exitoso")
                .error(null)
                .data(userResponseDTO)
                .build();

        assertEquals(responseDTO, responseDtoTwo);
    }
}