package nisum.user.com.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    protected String name;
    protected String email;
    protected String password;
    protected List<PhoneDTO> phones;
}
