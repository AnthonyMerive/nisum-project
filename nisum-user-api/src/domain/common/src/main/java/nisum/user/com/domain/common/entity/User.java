package nisum.user.com.domain.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean isActive;

}
