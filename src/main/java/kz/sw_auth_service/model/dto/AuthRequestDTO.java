package kz.sw_auth_service.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {

    @NotBlank(message = "Поле 'username' не может быть пустым.")
    private String username;
    @NotBlank(message = "Поле 'password' не может быть пустым.")
    private String password;
}
