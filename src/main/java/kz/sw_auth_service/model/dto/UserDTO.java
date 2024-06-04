package kz.sw_auth_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {

    private String uuid;
    @NotBlank(message = "Поле 'username' не может быть пустым.")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Поле 'password' не может быть пустым.")
    private String password;
    @NotBlank(message = "Поле 'firstName' не может быть пустым.")
    private String firstName;
    @NotBlank(message = "Поле 'lastName' не может быть пустым.")
    private String lastName;
}
