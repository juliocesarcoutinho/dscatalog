package br.com.topsystem.dscatalog.dtos.email;

import lombok.Getter;

import java.time.Instant;

public record CustomErrorDTO(Instant timestamp, Integer status, String error, String path) {
}
