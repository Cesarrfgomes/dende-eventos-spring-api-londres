package com.dendeframework.dendeeventos.londres.usuario_organizador.dto;

import com.dendeframework.dendeeventos.londres.empresa.dto.EmpresaDTO;

import java.time.LocalDate;

public record CriarUsuarioOrganizadorRequestDTO(
        String nome,
        LocalDate dataNascimento,
        String sexo,
        String email,
        EmpresaDTO empresa,
        String senha
) {
}
