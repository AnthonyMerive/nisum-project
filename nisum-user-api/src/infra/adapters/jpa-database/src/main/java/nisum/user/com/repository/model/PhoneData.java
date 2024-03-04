package nisum.user.com.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
public class PhoneData {

    @Id
    private String number;

    private String cityCode;

    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private UserData user;

}
