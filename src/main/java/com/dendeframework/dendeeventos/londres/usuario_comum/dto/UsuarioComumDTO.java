package com.dendeframework.dendeeventos.londres.usuario_comum.dto;

import com.dendeframework.dendeeventos.londres.usuario.model.TipoUsuario;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UsuarioComumDTO(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        String sexo,
        String idade,
        Boolean isAtivo,
        TipoUsuario tipoUsuario
) {
}
