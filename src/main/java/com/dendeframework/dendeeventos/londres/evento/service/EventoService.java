package com.dendeframework.dendeeventos.londres.evento.service;

import com.dendeframework.dendeeventos.londres.evento.dto.EventoDTO;
import com.dendeframework.dendeeventos.londres.evento.infra.EventoRepository;
import com.dendeframework.dendeeventos.londres.evento.mapper.EventoMapper;
import com.dendeframework.dendeeventos.londres.evento.model.Evento;
import com.dendeframework.dendeeventos.londres.exception.RecursoNaoEncontradoException;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.UsuarioOrganizadorDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.mapper.UsuarioOrganizadorMapper;
import com.dendeframework.dendeeventos.londres.usuario_organizador.service.UsuarioOrganizadorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final UsuarioOrganizadorService usuarioOrganizadorService;

    private final EventoMapper eventoMapper;

    public final UsuarioOrganizadorMapper organizadorMapper;

    public EventoService(EventoRepository eventoRepository, UsuarioOrganizadorService usuarioOrganizadorService) {
        this.eventoRepository = eventoRepository;
        this.usuarioOrganizadorService = usuarioOrganizadorService;
        this.eventoMapper = new EventoMapper();
        this.organizadorMapper = new UsuarioOrganizadorMapper();
    }

    @Transactional
    public EventoDTO criarEvento(EventoDTO eventoDTO) {
        UsuarioOrganizadorDTO organizador = this.usuarioOrganizadorService.getById(eventoDTO.organizador().id());

        Evento eventoPrincipal = null;
        if (eventoDTO.eventoPrincipal() != null && eventoDTO.eventoPrincipal().id() != null) {
            eventoPrincipal = this.buscarEventoPorId(eventoDTO.eventoPrincipal().id());
        }

        Evento evento = Evento.builder()
                .nome(eventoDTO.nome())
                .descricao(eventoDTO.descricao())
                .paginaWeb(eventoDTO.paginaWeb())
                .tipoEvento(eventoDTO.tipoEvento())
                .modalidade(eventoDTO.modalidadeEvento())
                .localEvento(eventoDTO.localEvento())
                .dataInicio(eventoDTO.dataInicio())
                .dataFim(eventoDTO.dataFim())
                .capacidadeMaxima(eventoDTO.capacidadeMaxima())
                .precoIngresso(eventoDTO.precoIngresso())
                .estornaIngresso(eventoDTO.estornaIngresso())
                .taxaEstorno(eventoDTO.taxaEstorno())
                .isAtivo(false)
                .organizador(organizadorMapper.toEntity(eventoDTO.organizador()))
                .eventoPrincipal(eventoPrincipal)
                .build();

        Evento novoEvento = this.eventoRepository.save(evento);

        return this.eventoMapper.toDTO(novoEvento);
    }

    private Evento buscarEventoPorId(Long id) {
        return this.eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento principal não encontrado"));
    }
}
