package br.com.topsystem.dscatalog.dtos.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank(message = "O campo email é obrigatório")
        @Email(message = "Favor digite um email válido")
        String email
) {
}
