package az.example.online.shopping.domain.valueobjects;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ROLE_ADMIN("ADMIN"),
    ROLE_MODERATOR("MODERATOR"),
    ROLE_USER("USER");
    private final String role;
    RoleEnum(String role) {
        this.role = role;
    }

}
