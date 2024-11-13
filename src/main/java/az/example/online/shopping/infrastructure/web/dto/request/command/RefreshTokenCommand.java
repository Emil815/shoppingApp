package az.example.online.shopping.infrastructure.web.dto.request.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RefreshTokenCommand {
    private String refreshToken;
}
