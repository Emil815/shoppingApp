package az.example.online.shopping.domain.mapper.abstracts;

import az.example.online.shopping.domain.roots.UserRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.web.dto.request.command.UserSignUpCommand;

public interface AbstractUserCommandMapper {
    UserRoot toRoot(UserSignUpCommand command);

    UserRoot toRoot(UserEntity entity);

    UserEntity toEntity(UserRoot root);
}
