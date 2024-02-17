package it.epicode.w6d4p.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotNull(message = "email obbligatoria")
    @Email(message = "scriver email valida")
    private String email;
    @NotNull(message = "password obbligatoria")
    @Size(min = 6)
    private String password;
    @NotNull(message = "nome obbligatorio")
    private String name;
    @NotNull(message = "cognome obbligatorio")
    private String surname;
}
