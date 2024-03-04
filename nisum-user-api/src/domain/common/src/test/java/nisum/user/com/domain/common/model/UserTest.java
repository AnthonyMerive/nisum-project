package nisum.user.com.domain.common.model;

import nisum.user.com.domain.common.entity.Phone;
import nisum.user.com.domain.common.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private static final Date TEST_DATE = new Date();

    Phone phone = Phone.builder()
            .number("3104206988")
            .cityCode("1")
            .countryCode("57")
            .build();

    Phone phoneTwo = Phone.builder()
            .number("3113659874")
            .cityCode("2")
            .countryCode("43")
            .build();

    List<Phone> phones = List.of(phone, phoneTwo);

    User user = User.builder()
            .id("ej345ea0-5636-11ee-8c99-0242ac104581")
            .name("Miguel Correa")
            .email("miguel@correa.org")
            .password("Hunter123")
            .phones(phones)
            .created(TEST_DATE)
            .modified(TEST_DATE)
            .lastLogin(TEST_DATE)
            .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bS11c2VyIiwiZXhwIjoxNjk1MDU2NjI2fQ.hpAV4met-ZyOtwrBVFO9xGHFt8-VlModnYoP4_uojtk")
            .isActive(true)
            .build();

    @Test
    void testEmptyInstance() {
        User user = new User();
        user.setName("Camilo Cifuentes");

        assertEquals("Camilo Cifuentes", user.getName());
    }

    @Test
    void testGetter() {
        assertEquals("miguel@correa.org", user.getEmail());
        assertEquals(phones, user.getPhones());
    }

    @Test
    void testEquals(){
        User userTwo = User.builder()
                .id("ej345ea0-5636-11ee-8c99-0242ac104581")
                .name("Miguel Correa")
                .email("miguel@correa.org")
                .password("Hunter123")
                .phones(phones)
                .created(TEST_DATE)
                .modified(TEST_DATE)
                .lastLogin(TEST_DATE)
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bS11c2VyIiwiZXhwIjoxNjk1MDU2NjI2fQ.hpAV4met-ZyOtwrBVFO9xGHFt8-VlModnYoP4_uojtk")
                .isActive(true)
                .build();

        assertEquals(user, userTwo);
    }
}