package com.dendeframework.dendeeventos.londres.evento.api;

import com.dendeframework.dendeeventos.londres.evento.dto.EventoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Evento", description = "Operações de cadastro e gestão e eventos")
@RequestMapping("/eventos")
public interface UsuarioComumEventoController {

    @Operation(summary = "Exibir o feed de eventos", description = "Uma listagem com todos os eventos ativos na plataforma ordenados pela data e hora do início de sua execução, assim como por ordem alfabética. Eventos que já finalizaram não devem aparecer no feed.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de eventos",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = EventoDTO.class))
                    )
            )
    })
    @GetMapping
    ResponseEntity<List<EventoDTO>> findFeedEventosAtivos();
}
