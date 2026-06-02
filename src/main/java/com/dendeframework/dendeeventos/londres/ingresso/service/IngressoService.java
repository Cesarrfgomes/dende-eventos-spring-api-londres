package com.dendeframework.dendeeventos.londres.ingresso.service;

import com.dendeframework.dendeeventos.londres.evento.infra.EventoRepository;
import com.dendeframework.dendeeventos.londres.evento.model.Evento;
import com.dendeframework.dendeeventos.londres.exception.CapacidadeMaximaAtingidaException;
import com.dendeframework.dendeeventos.londres.exception.ConflitoException;
import com.dendeframework.dendeeventos.londres.exception.RecursoNaoEncontradoException;
import com.dendeframework.dendeeventos.londres.ingresso.dto.CompraIngressoDTO;
import com.dendeframework.dendeeventos.londres.ingresso.dto.ComprarIngressoRequestDTO;
import com.dendeframework.dendeeventos.londres.ingresso.dto.IngressoDTO;
import com.dendeframework.dendeeventos.londres.ingresso.infra.IngressoRepository;
import com.dendeframework.dendeeventos.londres.ingresso.mapper.IngressoMapper;
import com.dendeframework.dendeeventos.londres.ingresso.model.Ingresso;
import com.dendeframework.dendeeventos.londres.ingresso.model.StatusIngresso;
import com.dendeframework.dendeeventos.londres.usuario.infra.UsuarioRepository;
import com.dendeframework.dendeeventos.londres.usuario.model.TipoUsuario;
import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioComum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngressoService {

    private final IngressoRepository ingressoRepository;

    private final EventoRepository eventoRepository;

    private final UsuarioRepository usuarioRepository;

    private final IngressoMapper ingressoMapper;

    public IngressoService(IngressoRepository ingressoRepository, EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.ingressoRepository = ingressoRepository;
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ingressoMapper = new IngressoMapper();
    }

    @Transactional
    public CompraIngressoDTO comprarIngresso(ComprarIngressoRequestDTO dto) {
        UsuarioComum usuario = this.buscarUsuarioComum(dto.usuarioComumId());

        Evento evento = this.buscarEventoAtivo(dto.eventoId());

        List<Ingresso> ingressos = new ArrayList<>();

        Evento eventoPrincipal = evento.getEventoPrincipal();
        if (eventoPrincipal != null) {
            this.validarCapacidade(eventoPrincipal);

            ingressos.add(this.novoIngresso(usuario, eventoPrincipal));
        }

        this.validarCapacidade(evento);

        ingressos.add(this.novoIngresso(usuario, evento));

        List<Ingresso> ingressosSalvos = this.ingressoRepository.saveAll(ingressos);

        Double valorTotal = ingressosSalvos.stream()
                .mapToDouble(Ingresso::getValorPago)
                .sum();

        List<IngressoDTO> ingressosDTO = ingressosSalvos.stream()
                .map(this.ingressoMapper::toDTO)
                .toList();

        return CompraIngressoDTO.builder()
                .valorTotal(valorTotal)
                .ingressos(ingressosDTO)
                .build();
    }

    public List<IngressoDTO> listarIngressosDoUsuario(Long usuarioComumId) {
        UsuarioComum usuario = this.buscarUsuarioComum(usuarioComumId);

        return this.ingressoRepository.findIngressosDoUsuarioOrdenados(usuario.getId()).stream()
                .map(this.ingressoMapper::toDTO)
                .toList();
    }

    private void validarCapacidade(Evento evento) {
        long ingressosAtivos = this.ingressoRepository.countByEventoIdAndStatus(evento.getId(), StatusIngresso.ATIVO);

        if (ingressosAtivos >= evento.getCapacidadeMaxima()) {
            throw new CapacidadeMaximaAtingidaException(
                    "O evento '" + evento.getNome() + "' atingiu a capacidade máxima de ingressos.");
        }
    }

    private Ingresso novoIngresso(UsuarioComum usuario, Evento evento) {
        return Ingresso.builder()
                .usuarioComum(usuario)
                .evento(evento)
                .valorPago(evento.getPrecoIngresso())
                .status(StatusIngresso.ATIVO)
                .valorEstornado(0.0)
                .dataCompra(LocalDateTime.now())
                .build();
    }

    private UsuarioComum buscarUsuarioComum(Long id) {
        return (UsuarioComum) this.usuarioRepository.findByIdAndTipoUsuario(id, TipoUsuario.COMUM.name())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    private Evento buscarEventoAtivo(Long id) {
        Evento evento = this.eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado"));

        if (!Boolean.TRUE.equals(evento.getIsAtivo())) {
            throw new ConflitoException("Não é possível comprar ingresso de um evento inativo.");
        }

        return evento;
    }
}