package br.com.topsystem.dscatalog.dtos.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO extends UserDTO {
    
    private String password;
    
    public UserInsertDTO() {
        super();
    }
    
}
