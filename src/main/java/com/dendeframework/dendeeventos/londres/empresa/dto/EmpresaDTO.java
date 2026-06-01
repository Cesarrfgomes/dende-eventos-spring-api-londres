package com.dendeframework.dendeeventos.londres.empresa.dto;

import lombok.Builder;

@Builder
public record EmpresaDTO(
        Long id,
        String cnpj,
        String razaoSocial,
        String nomeFantasia
) {
}