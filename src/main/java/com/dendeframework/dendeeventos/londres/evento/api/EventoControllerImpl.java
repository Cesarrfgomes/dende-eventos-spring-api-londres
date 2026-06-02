package com.dendeframework.dendeeventos.londres.evento.api;

import com.dendeframework.dendeeventos.londres.evento.dto.AtualizarEventoRequestDTO;
import com.dendeframework.dendeeventos.londres.evento.dto.CriarEventoRequestDTO;
import com.dendeframework.dendeeventos.londres.evento.dto.EventoDTO;
import com.dendeframework.dendeeventos.londres.evento.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoControllerImpl implements EventoController {

    private final EventoService service;

    public EventoControllerImpl(EventoService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<EventoDTO> criarEvento(Long organizadorId, CriarEventoRequestDTO dto) {
        EventoDTO novoEvento = this.service.criarEvento(organizadorId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }

    @Override
    public ResponseEntity<EventoDTO> atualizarEvento(Long organizadorId, Long eventoId, AtualizarEventoRequestDTO dto) {
        EventoDTO eventoAtualizado = this.service.atualizarEvento(organizadorId, eventoId, dto);

        return ResponseEntity.ok(eventoAtualizado);
    }

    @Override
    public ResponseEntity<Void> ativarEvento(Long organizadorId, Long eventoId) {
        this.service.ativarEvento(organizadorId, eventoId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> desativarEvento(Long organizadorId, Long eventoId) {
        this.service.desativarEvento(organizadorId, eventoId);

        return ResponseEntity.noContent().build();
    }
}