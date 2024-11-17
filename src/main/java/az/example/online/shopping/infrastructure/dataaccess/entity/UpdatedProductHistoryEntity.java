package az.example.online.shopping.infrastructure.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "updated_product_history")
public class UpdatedProductHistoryEntity extends BaseEntity {
    private String author;
    private UUID updatedFromId;
    private UUID updatedToId;
}
