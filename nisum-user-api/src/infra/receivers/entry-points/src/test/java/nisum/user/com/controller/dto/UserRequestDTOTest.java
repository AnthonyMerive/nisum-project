package nisum.user.com.controller.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserRequestDTOTest {

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

    UserRequestDTO userRequestDTO = UserRequestDTO.builder()
            .name("Miguel Correa")
            .email("miguel@correa.org")
            .password("Hunter123")
            .phones(phonesDTO)
            .build();

    @Test
    void testEmptyInstance() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Camilo Cifuentes");

        assertEquals("Camilo Cifuentes", userRequestDTO.getName());
    }

    @Test
    void testGetter() {
        assertEquals("miguel@correa.org", userRequestDTO.getEmail());
        assertEquals(phonesDTO, userRequestDTO.getPhones());
    }

    @Test
    void testEquals() {
        UserRequestDTO userDtoTwo = UserRequestDTO.builder()
                .name("Miguel Correa")
                .email("miguel@correa.org")
                .password("Hunter123")
                .phones(phonesDTO)
                .build();

        assertEquals(userRequestDTO, userDtoTwo);
    }
}