package az.example.online.shopping.infrastructure.dataaccess.entity;

import az.example.online.shopping.domain.valueobjects.RoleEnum;
import jakarta.persistence.*;
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
    @Column(nullable = false)
    private RoleEnum name;


}
