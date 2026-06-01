package com.dendeframework.dendeeventos.londres.usuario_organizador.dto;

import com.dendeframework.dendeeventos.londres.empresa.dto.EmpresaDTO;
import com.dendeframework.dendeeventos.londres.usuario.model.TipoUsuario;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UsuarioOrganizadorDTO(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        String sexo,
        String idade,
        Boolean isAtivo,
        TipoUsuario tipoUsuario,
        EmpresaDTO empresa
) {
}
