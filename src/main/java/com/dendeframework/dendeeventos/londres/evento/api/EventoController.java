package com.dendeframework.dendeeventos.londres.evento.api;

import com.dendeframework.dendeeventos.londres.evento.dto.CriarEventoRequestDTO;
import com.dendeframework.dendeeventos.londres.evento.dto.EventoDTO;
import com.dendeframework.dendeeventos.londres.exception.ExceptioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Evento", description = "Operações de cadastro e gestão e eventos")
@RequestMapping("/organizadores/{organizadorId}/eventos")
public interface EventoController {

    @Operation(summary = "Criar um evento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Evento criado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = EventoDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de requisição incorreta.",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Data de inicio não pode ser maior que a data final do evento",
                                    value = "{\"status\": 400, \"mensagem\": \"Data de inicio não pode ser maior que a data final do evento\"}"
                            )
                    )
            )
    })
    @PostMapping
    ResponseEntity<EventoDTO> criarEvento(@PathVariable("organizadorId") Long organizadorId, @RequestBody CriarEventoRequestDTO dto);

    @Operation(summary = "Ativar um evento", description = "Torna o evento visível na plataforma e libera a venda de ingressos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento ativado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Evento não encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Evento não encontrado",
                                    value = "{\"status\": 404, \"mensagem\": \"Evento não encontrado\"}"
                            )
                    )
            )
    })
    @PatchMapping("/{eventoId}/ativar")
    ResponseEntity<Void> ativarEvento(@PathVariable("organizadorId") Long organizadorId, @PathVariable("eventoId") Long eventoId);

    @Operation(summary = "Desativar um evento", description = "Remove o evento da plataforma e suspende a venda de ingressos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento desativado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Evento não encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Evento não encontrado",
                                    value = "{\"status\": 404, \"mensagem\": \"Evento não encontrado\"}"
                            )
                    )
            )
    })
    @PatchMapping("/{eventoId}/desativar")
    ResponseEntity<Void> desativarEvento(@PathVariable("organizadorId") Long organizadorId, @PathVariable("eventoId") Long eventoId);
}
