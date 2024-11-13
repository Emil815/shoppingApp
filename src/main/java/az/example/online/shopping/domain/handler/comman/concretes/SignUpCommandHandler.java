package az.example.online.shopping.domain.handler.comman.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractRoleCommandMapper;
import az.example.online.shopping.domain.mapper.abstracts.AbstractUserCommandMapper;
import az.example.online.shopping.domain.roots.UserRoot;
import az.example.online.shopping.domain.valueobjects.RoleEnum;
import az.example.online.shopping.infrastructure.dataaccess.entity.RoleEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.RoleRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.UserRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.UserSignUpCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SignUpCommandHandler {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AbstractUserCommandMapper userCommandMapper;
    private final AbstractRoleCommandMapper roleCommandMapper;


    @Transactional
    public void handle(UserSignUpCommand command) {
        UserRoot userRoot = userCommandMapper.toRoot(command);
        if (userRepository.findByPhoneNumber(userRoot.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        RoleEntity userRole = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Role does not exist"));
        userRoot.initialize();
        userRoot.setRoles(Set.of(roleCommandMapper.toRoot(userRole)));
        userRoot.setPassword(passwordEncoder.encode(userRoot.getPassword()));
        userRepository.save(userCommandMapper.toEntity(userRoot));
    }
}
