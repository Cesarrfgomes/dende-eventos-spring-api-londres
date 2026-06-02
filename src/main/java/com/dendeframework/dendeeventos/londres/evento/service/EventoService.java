package com.dendeframework.dendeeventos.londres.evento.service;

import com.dendeframework.dendeeventos.londres.evento.dto.CriarEventoRequestDTO;
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

import java.util.List;

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
    public EventoDTO criarEvento(Long organizadorId, CriarEventoRequestDTO dto) {
        UsuarioOrganizadorDTO organizador = this.usuarioOrganizadorService.getById(organizadorId);

        Evento eventoPrincipal = null;
        if (dto.eventoPrincipalId() != null) {
            eventoPrincipal = this.buscarEventoPorId(dto.eventoPrincipalId());
        }

        Evento evento = Evento.builder()
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
                .isAtivo(false)
                .organizador(organizadorMapper.toEntity(organizador))
                .eventoPrincipal(eventoPrincipal)
                .build();

        Evento novoEvento = this.eventoRepository.save(evento);

        return this.eventoMapper.toDTO(novoEvento);
    }

    @Transactional
    public void ativarEvento(Long usuarioOrganizadorId, Long eventoId) {
        Evento evento = this.buscarEventoDoOrganizador(eventoId, usuarioOrganizadorId);

        evento.setIsAtivo(true);

        this.eventoRepository.save(evento);
    }

    @Transactional
    public void desativarEvento(Long usuarioOrganizadorId, Long eventoId) {
        Evento evento = this.buscarEventoDoOrganizador(eventoId, usuarioOrganizadorId);

        evento.setIsAtivo(false);

        this.eventoRepository.save(evento);
    }

    private Evento buscarEventoDoOrganizador(Long eventoId, Long organizadorId) {
        return this.eventoRepository.findByIdAndOrganizadorId(eventoId, organizadorId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado"));
    }

    private Evento buscarEventoPorId(Long id) {
        return this.eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento principal não encontrado"));
    }
}
