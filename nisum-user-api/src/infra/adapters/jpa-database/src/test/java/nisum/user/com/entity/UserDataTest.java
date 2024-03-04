package nisum.user.com.entity;

import nisum.user.com.repository.model.PhoneData;
import nisum.user.com.repository.model.UserData;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDataTest {

    private static final Date TEST_DATE = new Date();

    PhoneData phoneData = PhoneData.builder()
            .number("3104206988")
            .cityCode("1")
            .countryCode("57")
            .build();

    PhoneData phoneDataTwo = PhoneData.builder()
            .number("3113659874")
            .cityCode("2")
            .countryCode("43")
            .build();

    List<PhoneData> phonesData = List.of(phoneData, phoneDataTwo);

    UserData userData = UserData.builder()
            .id("ej345ea0-5636-11ee-8c99-0242ac104581")
            .name("Miguel Correa")
            .email("miguel@correa.org")
            .password("Hunter123")
            .phones(phonesData)
            .created(TEST_DATE)
            .modified(TEST_DATE)
            .lastLogin(TEST_DATE)
            .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bS11c2VyIiwiZXhwIjoxNjk1MDU2NjI2fQ.hpAV4met-ZyOtwrBVFO9xGHFt8-VlModnYoP4_uojtk")
            .isActive(true)
            .build();

    @Test
    void testEmptyInstance() {
        UserData userData = new UserData();
        userData.setName("Camilo Cifuentes");

        assertEquals("Camilo Cifuentes", userData.getName());
    }

    @Test
    void testGetter() {
        assertEquals("miguel@correa.org", userData.getEmail());
        assertEquals(phonesData, userData.getPhones());
    }

    @Test
    void testEquals(){
        UserData userDataTwo = UserData.builder()
                .id("ej345ea0-5636-11ee-8c99-0242ac104581")
                .name("Miguel Correa")
                .email("miguel@correa.org")
                .password("Hunter123")
                .phones(phonesData)
                .created(TEST_DATE)
                .modified(TEST_DATE)
                .lastLogin(TEST_DATE)
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bS11c2VyIiwiZXhwIjoxNjk1MDU2NjI2fQ.hpAV4met-ZyOtwrBVFO9xGHFt8-VlModnYoP4_uojtk")
                .isActive(true)
                .build();

        assertEquals(userData, userDataTwo);
    }
}