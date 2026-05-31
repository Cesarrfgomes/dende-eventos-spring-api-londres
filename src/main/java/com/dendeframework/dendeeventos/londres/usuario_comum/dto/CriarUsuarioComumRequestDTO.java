package com.dendeframework.dendeeventos.londres.usuario_comum.dto;

import java.time.LocalDate;

public record CriarUsuarioComumRequestDTO(
        String nome,
        LocalDate dataNascimento,
        String sexo,
        String email,
        String senha,
        Boolean isAtivo
) {
}
