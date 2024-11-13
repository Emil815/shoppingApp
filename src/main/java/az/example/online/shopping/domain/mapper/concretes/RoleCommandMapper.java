package az.example.online.shopping.domain.mapper.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractRoleCommandMapper;
import az.example.online.shopping.domain.roots.RoleRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleCommandMapper implements AbstractRoleCommandMapper {

    @Override
    public RoleRoot toRoot(RoleEntity entity) {
        return RoleRoot.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public RoleEntity toEntity(RoleRoot root) {
        return RoleEntity
                .builder()
                .id(root.getId())
                .name(root.getName())
                .build();
    }
}
