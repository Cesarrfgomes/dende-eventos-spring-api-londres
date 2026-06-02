package com.dendeframework.dendeeventos.londres.evento.api;

import com.dendeframework.dendeeventos.londres.evento.dto.EventoDTO;
import com.dendeframework.dendeeventos.londres.evento.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioComumEventoControllerImpl implements UsuarioComumEventoController {

    private final EventoService eventoService;

    public UsuarioComumEventoControllerImpl(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Override
    public ResponseEntity<List<EventoDTO>> findFeedEventosAtivos() {
        List<EventoDTO> eventos = this.eventoService.findFeedEventosAtivos();
        return ResponseEntity.ok(eventos);
    }
}
