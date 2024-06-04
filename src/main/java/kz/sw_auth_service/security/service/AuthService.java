package kz.sw_auth_service.security.service;

import kz.sw_auth_service.exception.CustomException;
import kz.sw_auth_service.mapper.UserMapper;
import kz.sw_auth_service.model.dto.AuthRequestDTO;
import kz.sw_auth_service.model.dto.AuthResponseDTO;
import kz.sw_auth_service.model.dto.UserDTO;
import kz.sw_auth_service.model.entity.UserEntity;
import kz.sw_auth_service.repository.UserRepository;
import kz.sw_auth_service.util.MessageSource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserDTO registration(UserDTO userDTO) throws CustomException {
        String username = userDTO.getUsername().toLowerCase().trim();
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(MessageSource.USERNAME_ALREADY_EXISTS.getMessage(username))
                    .build();
        }

        UserEntity entity = new UserEntity();
        entity.setUuid(UUID.randomUUID().toString());
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        entity.setFirstName(userDTO.getFirstName());
        entity.setLastName(userDTO.getLastName());
        entity.setCreatedBy(username);
        entity.setUpdatedBy(username);
        entity = userRepository.save(entity);

        return userMapper.toDTO(entity);
    }

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);

        return new AuthResponseDTO(token);
    }
}
