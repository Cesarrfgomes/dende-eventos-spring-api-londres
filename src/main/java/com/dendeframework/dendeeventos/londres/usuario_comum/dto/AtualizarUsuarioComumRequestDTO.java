package com.dendeframework.dendeeventos.londres.usuario_comum.dto;

import java.time.LocalDate;

public record AtualizarUsuarioComumRequestDTO(
        String nome,
        LocalDate dataNascimento,
        String sexo
) {
}