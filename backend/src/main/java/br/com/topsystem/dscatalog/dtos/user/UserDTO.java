package br.com.topsystem.dscatalog.dtos.user;

import br.com.topsystem.dscatalog.dtos.RoleDTO;
import br.com.topsystem.dscatalog.entities.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    
    @Setter(AccessLevel.NONE)
    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User entity) {
        id         = entity.getId();
        firstName  = entity.getFirstName();
        lastName   = entity.getLastName();
        email      = entity.getEmail();
        entity.getRoles().forEach(role -> roles.add(new RoleDTO(role)));
    }
}
