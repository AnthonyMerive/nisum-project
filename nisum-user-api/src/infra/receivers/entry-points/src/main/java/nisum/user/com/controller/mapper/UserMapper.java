package nisum.user.com.controller.mapper;

import nisum.user.com.controller.dto.PhoneDTO;
import nisum.user.com.controller.dto.UserRequestDTO;
import nisum.user.com.controller.dto.UserResponseDTO;
import nisum.user.com.domain.common.entity.Phone;
import nisum.user.com.domain.common.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponseDTO mapUserToDTO(User user){
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phones(mapPhonesToDto(user.getPhones()))
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .isActive(user.getIsActive())
                .build();
    }

    public static User mapDtoToUser(UserRequestDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phones(mapDtoToPhones(userDTO.getPhones()))
                .build();
    }

    private static List<PhoneDTO> mapPhonesToDto(List<Phone> phones){
        return phones.stream().map(phone -> PhoneDTO.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build()
        ).collect(Collectors.toList());
    }

    private static List<Phone> mapDtoToPhones(List<PhoneDTO> phonesDTO){
        return phonesDTO.stream().map(phoneDTO -> Phone.builder()
                .number(phoneDTO.getNumber())
                .cityCode(phoneDTO.getCityCode())
                .countryCode(phoneDTO.getCountryCode())
                .build()
        ).collect(Collectors.toList());
    }
}
