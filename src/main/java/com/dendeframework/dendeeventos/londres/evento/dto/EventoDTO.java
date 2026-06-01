package com.dendeframework.dendeeventos.londres.evento.dto;

import com.dendeframework.dendeeventos.londres.evento.model.ModalidadeEvento;
import com.dendeframework.dendeeventos.londres.evento.model.TipoEvento;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.UsuarioOrganizadorDTO;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventoDTO(
        Long id,
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
        Boolean isAtivo,
        LocalDateTime dataCadastro,
        UsuarioOrganizadorDTO organizador,
        EventoDTO eventoPrincipal
) {
}
