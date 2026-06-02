package com.dendeframework.dendeeventos.londres.evento.dto;

import com.dendeframework.dendeeventos.londres.evento.model.ModalidadeEvento;
import com.dendeframework.dendeeventos.londres.evento.model.TipoEvento;

import java.time.LocalDateTime;

public record AtualizarEventoRequestDTO(
        String nome,
        String descricao,
        String paginaWeb,
        TipoEvento tipoEvento,
        ModalidadeEvento modalidadeEvento,
        String localEvento,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Integer capacidadeMaxima,
        Double precoIngresso,
        Boolean estornaIngresso,
        Double taxaEstorno,
        Long eventoPrincipalId
) {
}