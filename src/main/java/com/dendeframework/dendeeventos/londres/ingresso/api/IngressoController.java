package com.dendeframework.dendeeventos.londres.ingresso.api;

import com.dendeframework.dendeeventos.londres.exception.ExceptioDTO;
import com.dendeframework.dendeeventos.londres.ingresso.dto.CompraIngressoDTO;
import com.dendeframework.dendeeventos.londres.ingresso.dto.ComprarIngressoRequestDTO;
import com.dendeframework.dendeeventos.londres.ingresso.dto.IngressoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Ingresso", description = "Operações de compra de ingressos")
@RequestMapping("/ingressos")
public interface IngressoController {

    @Operation(
            summary = "Comprar ingresso de um evento",
            description = "Gera o ingresso para o evento desejado. Caso o evento esteja vinculado a um evento principal, "
                    + "são gerados dois ingressos (um para cada evento) e o valor total é a soma dos preços dos dois eventos."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ingresso(s) gerado(s) com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = CompraIngressoDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário ou evento não encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Evento não encontrado",
                                    value = "{\"status\": 404, \"mensagem\": \"Evento não encontrado\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Evento inativo",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Evento inativo",
                                    value = "{\"status\": 409, \"mensagem\": \"Não é possível comprar ingresso de um evento inativo.\"}"
                            )
                    )
            )
    })
    @PostMapping
    ResponseEntity<CompraIngressoDTO> comprarIngresso(@RequestBody ComprarIngressoRequestDTO dto);

    @Operation(
            summary = "Listar ingressos do usuário comum",
            description = "Retorna todos os ingressos comprados pelo usuário. Primeiro os ingressos ativos de eventos "
                    + "ainda ativos e não realizados; por último os ingressos cancelados ou de eventos já finalizados. "
                    + "Dentro de cada grupo, ordenados pela data de início do evento e pelo nome do evento."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ingressos listados com sucesso",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = IngressoDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Usuário não encontrado",
                                    value = "{\"status\": 404, \"mensagem\": \"Usuário não encontrado\"}"
                            )
                    )
            )
    })
    @GetMapping("/usuario/{usuarioComumId}")
    ResponseEntity<List<IngressoDTO>> listarIngressosDoUsuario(@PathVariable("usuarioComumId") Long usuarioComumId);

    @Operation(
            summary = "Cancelar um ingresso",
            description = "Cancela um ingresso comprado pelo usuário. O ingresso é marcado como cancelado, o valor pago é "
                    + "estornado conforme as regras do evento e a vaga volta a ficar disponível para venda."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ingresso cancelado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = IngressoDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário ou ingresso não encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Ingresso não encontrado",
                                    value = "{\"status\": 404, \"mensagem\": \"Ingresso não encontrado\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Ingresso já cancelado",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Ingresso já cancelado",
                                    value = "{\"status\": 409, \"mensagem\": \"Ingresso já está cancelado.\"}"
                            )
                    )
            )
    })
    @PatchMapping("/{ingressoId}/cancelar")
    ResponseEntity<IngressoDTO> cancelarIngresso(@PathVariable("ingressoId") Long ingressoId, @RequestParam("usuarioComumId") Long usuarioComumId);
}