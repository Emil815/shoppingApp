package az.example.online.shopping.domain.roots;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SubCategoryRoot extends BaseRoot<UUID> {
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryRoot category;
}
