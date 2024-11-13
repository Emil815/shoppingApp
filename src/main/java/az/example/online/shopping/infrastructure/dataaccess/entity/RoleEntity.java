package az.example.online.shopping.infrastructure.dataaccess.entity;

import az.example.online.shopping.domain.valueobjects.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity  {


    @Enumerated(EnumType.STRING)
    private RoleEnum name;


}
