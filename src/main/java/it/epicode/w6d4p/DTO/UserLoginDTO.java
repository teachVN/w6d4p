package it.epicode.w6d4p.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDTO {
    @Email(message = "scriver email valida")
    private String email;
    @NotNull(message = "password obbligatoria")
    @Size(min = 6)
    private String password;
}
