package kz.sw_auth_service.controller;

import jakarta.validation.Valid;
import kz.sw_auth_service.exception.CustomException;
import kz.sw_auth_service.model.dto.AuthRequestDTO;
import kz.sw_auth_service.model.dto.AuthResponseDTO;
import kz.sw_auth_service.model.dto.UserDTO;
import kz.sw_auth_service.security.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public UserDTO registration(@RequestBody @Valid UserDTO userDTO) throws CustomException {
        log.info("Incoming registration request: {}.", userDTO.getUsername());
        return authService.registration(userDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        log.info("Incoming login request: {}.", authRequestDTO.getUsername());
        return authService.login(authRequestDTO);
    }
}
