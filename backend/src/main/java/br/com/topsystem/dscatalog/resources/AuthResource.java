package br.com.topsystem.dscatalog.resources;

import br.com.topsystem.dscatalog.dtos.email.EmailDTO;
import br.com.topsystem.dscatalog.dtos.email.NewPasswordDTO;
import br.com.topsystem.dscatalog.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/new-password")
    public ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordDTO body) {
        service.saveNewPassword(body);
        return ResponseEntity.noContent().build();
    }
}