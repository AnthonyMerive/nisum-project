package nisum.user.com.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneDTOTest {

    PhoneDTO phoneDTO = PhoneDTO.builder()
            .number("3104206988")
            .cityCode("1")
            .countryCode("57")
            .build();

    @Test
    void testEmptyInstance() {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("3145456325");

        assertEquals("3145456325", phoneDTO.getNumber());
    }

    @Test
    void testGetter() {
        assertEquals("1", phoneDTO.getCityCode());
        assertEquals("57", phoneDTO.getCountryCode());
    }

    @Test
    void testEquals(){
        PhoneDTO phoneDtoTwo = PhoneDTO.builder()
                .number("3104206988")
                .cityCode("1")
                .countryCode("57")
                .build();

        assertEquals(phoneDTO, phoneDtoTwo);
    }
}