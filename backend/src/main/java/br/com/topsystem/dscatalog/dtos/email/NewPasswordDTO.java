package br.com.topsystem.dscatalog.dtos.email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewPasswordDTO(

        @NotBlank(message = "O campo token é obrigatório")
        String token,

        @NotBlank(message = "O campo senha é obrigatório")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String password
) {
}
