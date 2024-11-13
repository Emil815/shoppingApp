package az.example.online.shopping.domain.roots;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public abstract class BaseRoot<ID> {
    private ID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
}
