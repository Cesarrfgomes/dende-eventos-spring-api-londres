package com.dendeframework.dendeeventos.londres.ingresso.dto;

import com.dendeframework.dendeeventos.londres.ingresso.model.StatusIngresso;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record IngressoDTO(
        Long id,
        Double valorPago,
        StatusIngresso status,
        Double valorEstornado,
        LocalDateTime dataCompra,
        LocalDateTime dataCancelamento,
        Long usuarioComumId,
        Long eventoId,
        String eventoNome
) {
}