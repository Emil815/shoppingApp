package az.example.online.shopping.infrastructure.web.dto.request.command;

import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpCommand {
    private String name;
    private String surname;
    private String password;
    private String phoneNumber;
    private Boolean isWholeSale;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(name)
                .surname(surname)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();
    }
}
