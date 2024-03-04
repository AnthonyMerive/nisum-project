package nisum.user.com.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends UserRequestDTO {
    private String id;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
}
