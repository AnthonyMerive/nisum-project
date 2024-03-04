package nisum.user.com.entity;

import nisum.user.com.repository.model.PhoneData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneDataTest {

    PhoneData phoneData = PhoneData.builder()
            .number("3104206988")
            .cityCode("1")
            .countryCode("57")
            .build();

    @Test
    void testEmptyInstance() {
        PhoneData phoneData = new PhoneData();
        phoneData.setNumber("3145456325");

        assertEquals("3145456325", phoneData.getNumber());
    }

    @Test
    void testGetter() {
        assertEquals("1", phoneData.getCityCode());
        assertEquals("57", phoneData.getCountryCode());
    }

    @Test
    void testEquals(){
        PhoneData phoneDataTwo = PhoneData.builder()
                .number("3104206988")
                .cityCode("1")
                .countryCode("57")
                .build();

        assertEquals(phoneData, phoneDataTwo);
    }
}