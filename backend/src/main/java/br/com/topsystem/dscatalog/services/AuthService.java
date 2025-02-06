package br.com.topsystem.dscatalog.services;

import br.com.topsystem.dscatalog.dtos.email.EmailDTO;
import br.com.topsystem.dscatalog.dtos.email.NewPasswordDTO;
import br.com.topsystem.dscatalog.entities.PasswordRecover;
import br.com.topsystem.dscatalog.repositories.PasswordRecoverRepository;
import br.com.topsystem.dscatalog.repositories.UserRepository;
import br.com.topsystem.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recorverUri;

    private final UserRepository userRepository;
    private final PasswordRecoverRepository passwordRecoverRepository;
    private final EmailService emailService;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordRecoverRepository passwordRecoverRepository,
                       EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordRecoverRepository = passwordRecoverRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createRecoveryToken(EmailDTO body) {
        var user = userRepository.findByEmail(body.email());
        if (user == null) {
            throw new ResourceNotFoundExceptions("Email não encontrado");
        }

        String token = UUID.randomUUID().toString();

        var entity = new PasswordRecover();
        entity.setEmail(body.email());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity = passwordRecoverRepository.save(entity);

        String message = STR."Acesse o link para gerar uma nova senha\n\n\{recorverUri}\{token}.\n\nObs. A validade para alteração é de \{tokenMinutes} minutos";
        emailService.sendEmail(body.email(), "Recuperação de senha", message );
    }

    @Transactional
    public void saveNewPassword(NewPasswordDTO body) {
        List<PasswordRecover> result = passwordRecoverRepository.searchValidTokens(body.token(), Instant.now());
        if (result.isEmpty()) {
            throw new ResourceNotFoundExceptions("Token inválido");
        }

        var user = userRepository.findByEmail(result.get(0).getEmail());
        user.setPassword(passwordEncoder.encode(body.password()));
        user = userRepository.save(user);
    }
}
