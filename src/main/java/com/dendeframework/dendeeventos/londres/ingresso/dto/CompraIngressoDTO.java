package com.dendeframework.dendeeventos.londres.ingresso.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CompraIngressoDTO(
        Double valorTotal,
        List<IngressoDTO> ingressos
) {
}