package br.com.topsystem.dscatalog.resources;

import br.com.topsystem.dscatalog.dtos.email.EmailDTO;
import br.com.topsystem.dscatalog.dtos.user.UserDTO;
import br.com.topsystem.dscatalog.dtos.user.UserInsertDTO;
import br.com.topsystem.dscatalog.dtos.user.UserUpdateDTO;
import br.com.topsystem.dscatalog.services.AuthService;
import br.com.topsystem.dscatalog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    private final AuthService service;

    public AuthResource(AuthService service) {
        this.service = service;
    }

    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoveryToken(@Valid @RequestBody EmailDTO body) {
        service.createRecoveryToken(body);
        return ResponseEntity.noContent().build();
    }
}