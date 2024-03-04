package nisum.user.com.controller.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserResponseDTOTest {

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

    @Test
    void testEmptyInstance() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName("Camilo Cifuentes");

        assertEquals("Camilo Cifuentes", userResponseDTO.getName());
    }

    @Test
    void testGetter() {
        assertEquals("miguel@correa.org", userResponseDTO.getEmail());
        assertEquals(phonesDTO, userResponseDTO.getPhones());
    }

    @Test
    void testEquals() {
        UserResponseDTO userDataTwo = UserResponseDTO.builder()
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

        assertEquals(userResponseDTO, userDataTwo);
    }
}