package br.com.topsystem.dscatalog.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Entity
@Data
@Table(name = "tb_password_recover")
@EqualsAndHashCode(of = "id")
public class PasswordRecover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Instant expiration;
}
