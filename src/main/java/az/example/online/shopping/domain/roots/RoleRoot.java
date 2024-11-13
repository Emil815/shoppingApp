package az.example.online.shopping.domain.roots;

import az.example.online.shopping.domain.valueobjects.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class RoleRoot extends BaseRoot<UUID> implements GrantedAuthority {
    private RoleEnum name;

    @Override
    public String getAuthority() {
        return this.name.name();
    }
}
