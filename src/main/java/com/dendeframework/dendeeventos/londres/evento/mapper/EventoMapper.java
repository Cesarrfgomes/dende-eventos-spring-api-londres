package com.dendeframework.dendeeventos.londres.evento.mapper;

import com.dendeframework.dendeeventos.londres.evento.dto.EventoDTO;
import com.dendeframework.dendeeventos.londres.evento.model.Evento;
import com.dendeframework.dendeeventos.londres.usuario_organizador.mapper.UsuarioOrganizadorMapper;

public class EventoMapper {

    private final UsuarioOrganizadorMapper usuarioOrganizadorMapper;

    public EventoMapper() {
        this.usuarioOrganizadorMapper = new UsuarioOrganizadorMapper();
    }

    public EventoDTO toDTO(Evento entity) {
        if (entity == null) {
            return null;
        }

        return EventoDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .paginaWeb(entity.getPaginaWeb())
                .tipoEvento(entity.getTipoEvento())
                .modalidadeEvento(entity.getModalidade())
                .localEvento(entity.getLocalEvento())
                .dataInicio(entity.getDataInicio())
                .dataFim(entity.getDataFim())
                .capacidadeMaxima(entity.getCapacidadeMaxima())
                .precoIngresso(entity.getPrecoIngresso())
                .estornaIngresso(entity.getEstornaIngresso())
                .taxaEstorno(entity.getTaxaEstorno())
                .isAtivo(entity.getIsAtivo())
                .dataCadastro(entity.getDataCadastro())
                .organizador(this.usuarioOrganizadorMapper.toDTO(entity.getOrganizador()))
                .eventoPrincipal(this.toEventoPrincipalDTO(entity.getEventoPrincipal()))
                .build();
    }

    public Evento toEntity(EventoDTO dto) {
        if (dto == null) {
            return null;
        }

        return Evento.builder()
                .id(dto.id())
                .nome(dto.nome())
                .descricao(dto.descricao())
                .paginaWeb(dto.paginaWeb())
                .tipoEvento(dto.tipoEvento())
                .modalidade(dto.modalidadeEvento())
                .localEvento(dto.localEvento())
                .dataInicio(dto.dataInicio())
                .dataFim(dto.dataFim())
                .capacidadeMaxima(dto.capacidadeMaxima())
                .precoIngresso(dto.precoIngresso())
                .estornaIngresso(dto.estornaIngresso())
                .taxaEstorno(dto.taxaEstorno())
                .isAtivo(dto.isAtivo())
                .dataCadastro(dto.dataCadastro())
                .organizador(this.usuarioOrganizadorMapper.toEntity(dto.organizador()))
                .build();
    }

    private EventoDTO toEventoPrincipalDTO(Evento eventoPrincipal) {
        if (eventoPrincipal == null) {
            return null;
        }

        return EventoDTO.builder()
                .id(eventoPrincipal.getId())
                .nome(eventoPrincipal.getNome())
                .descricao(eventoPrincipal.getDescricao())
                .tipoEvento(eventoPrincipal.getTipoEvento())
                .modalidadeEvento(eventoPrincipal.getModalidade())
                .localEvento(eventoPrincipal.getLocalEvento())
                .dataInicio(eventoPrincipal.getDataInicio())
                .dataFim(eventoPrincipal.getDataFim())
                .isAtivo(eventoPrincipal.getIsAtivo())
                .build();
    }
}