package az.example.online.shopping.domain.mapper.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractUserCommandMapper;
import az.example.online.shopping.domain.roots.RoleRoot;
import az.example.online.shopping.domain.roots.UserRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.RoleEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.web.dto.request.command.UserSignUpCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserCommandMapper implements AbstractUserCommandMapper {
    private final RoleCommandMapper roleCommandMapper;

    @Override
    public UserRoot toRoot(UserSignUpCommand command) {
        return UserRoot
                .builder()
                .name(command.getName())
                .surname(command.getSurname())
                .password(command.getPassword())
                .phoneNumber(command.getPhoneNumber())
                .build();
    }

    @Override
    public UserRoot toRoot(UserEntity entity) {
        return UserRoot
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .phoneNumber(entity.getPhoneNumber())
                .password(entity.getPassword())
                .roles(Objects.requireNonNullElse(entity.getRoles(), new HashSet<RoleEntity>())
                        .stream()
                        .map(this.roleCommandMapper::toRoot)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public UserEntity toEntity(UserRoot root) {
        return UserEntity
                .builder()
                .id(root.getId())
                .name(root.getName())
                .surname(root.getSurname())
                .password(root.getPassword())
                .phoneNumber(root.getPhoneNumber())
                .roles(Objects.requireNonNullElse(root.getRoles(), new HashSet<RoleRoot>())
                        .stream()
                        .map(this.roleCommandMapper::toEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
