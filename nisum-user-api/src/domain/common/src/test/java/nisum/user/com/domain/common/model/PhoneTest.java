package nisum.user.com.domain.common.model;

import nisum.user.com.domain.common.entity.Phone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneTest {

    Phone phone = Phone.builder()
            .number("3104206988")
            .cityCode("1")
            .countryCode("57")
            .build();

    @Test
    void testEmptyInstance() {
        Phone phone = new Phone();
        phone.setNumber("3145456325");

        assertEquals("3145456325", phone.getNumber());
    }

    @Test
    void testGetter() {
        assertEquals("1", phone.getCityCode());
        assertEquals("57", phone.getCountryCode());
    }

    @Test
    void testEquals(){
        Phone phoneTwo = Phone.builder()
                .number("3104206988")
                .cityCode("1")
                .countryCode("57")
                .build();

        assertEquals(phone, phoneTwo);
    }
}