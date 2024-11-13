package az.example.online.shopping.domain.mapper.abstracts;

import az.example.online.shopping.domain.roots.RoleRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.RoleEntity;

public interface AbstractRoleCommandMapper {
    RoleRoot toRoot(RoleEntity entity);

    RoleEntity toEntity(RoleRoot root);
}
