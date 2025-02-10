package az.example.online.shopping.infrastructure.web.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoResponseModel {
    private String name;
    private String surname;
    private String password;
    private String phoneNumber;
    private Boolean isWholeSale;
}
