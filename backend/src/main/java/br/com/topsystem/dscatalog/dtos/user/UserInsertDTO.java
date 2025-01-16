package br.com.topsystem.dscatalog.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO extends UserDTO {
    
    @NotBlank(message = "O campo senha é obrigatório")
    private String password;
    
    public UserInsertDTO() {
        super();
    }
    
}
