package nisum.user.com.repository.mapper;

import nisum.user.com.domain.common.entity.Phone;
import nisum.user.com.domain.common.entity.User;
import nisum.user.com.repository.model.PhoneData;
import nisum.user.com.repository.model.UserData;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserRepositoryMapper {

    public static UserData mapUserEntityToCreateData(User user){
        var userData = UserData.builder()
                .id(UUID.randomUUID().toString())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(new Date())
                .modified(new Date())
                .lastLogin(new Date())
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .build();

        userData.setPhones(mapPhonesEntityToData(user.getPhones(), userData));

        return userData;
    }

    public static UserData mapUserEntityToUpdateData(User userUpdate, User userExist){
        var userData = UserData.builder()
                .id(userExist.getId())
                .name(userUpdate.getName())
                .email(userUpdate.getEmail())
                .password(userUpdate.getPassword())
                .created(userExist.getCreated())
                .modified(new Date())
                .lastLogin(new Date())
                .token(userExist.getToken())
                .isActive(true)
                .build();

        userData.setPhones(mapPhonesEntityToData(userUpdate.getPhones(), userData));

        return userData;
    }


    public static User mapDataToUserEntity(UserData userData){
        return User.builder()
                .id(userData.getId())
                .name(userData.getName())
                .email(userData.getEmail())
                .password(userData.getPassword())
                .phones(mapDataToPhonesEntity(userData.getPhones()))
                .created(userData.getCreated())
                .modified(userData.getModified())
                .lastLogin(userData.getLastLogin())
                .token(userData.getToken())
                .isActive(userData.getIsActive())
                .build();
    }

    private static List<PhoneData> mapPhonesEntityToData(List<Phone> phones, UserData user){
        return phones.stream().map(phone -> PhoneData.builder()
                .user(user)
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build()
        ).toList();
    }

    private static List<Phone> mapDataToPhonesEntity(List<PhoneData> phoneData){
        return phoneData.stream().map(phone -> Phone.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build()
        ).toList();
    }
}
