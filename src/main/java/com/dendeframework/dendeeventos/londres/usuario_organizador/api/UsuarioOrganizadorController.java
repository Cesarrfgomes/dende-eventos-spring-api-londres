package com.dendeframework.dendeeventos.londres.usuario_organizador.api;

import com.dendeframework.dendeeventos.londres.exception.ExceptioDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.AtualizarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.CriarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.ReativarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.UsuarioOrganizadorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuário Organizador", description = "Operações de cadastro e gestão de usuários organizadores")
@RequestMapping("/usuarios/organizadores")
public interface UsuarioOrganizadorController {

    @Operation(summary = "Cadastrar usuário organizador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário criado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = UsuarioOrganizadorDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "E-mail já cadastrado",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "E-mail já cadastrado",
                                    value = "{\"status\": 409, \"mensagem\": \"Email já cadastrado\"}"
                            )
                    )
            )
    })
    @PostMapping
    ResponseEntity<UsuarioOrganizadorDTO> criar(@RequestBody CriarUsuarioOrganizadorRequestDTO dto);

    @Operation(summary = "Visualizar perfil do usuário organizador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfil encontrado",
                    content = @Content(
                            schema = @Schema(implementation = UsuarioOrganizadorDTO.class)
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
    @GetMapping("/{id}")
    ResponseEntity<UsuarioOrganizadorDTO> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Atualizar dados cadastrais do usuário organizador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dados atualizados com sucesso"
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
    @PutMapping("/{id}")
    ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AtualizarUsuarioOrganizadorRequestDTO dto);

    @Operation(summary = "Desativar usuário organizador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário desativado"
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
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Organizador possui eventos ativos",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Organizador com eventos ativos",
                                    value = "{\"status\": 409, \"mensagem\": \"O organizador tem eventos ativos, não foi possível desativa-lo.\"}"
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> desativar(@PathVariable Long id);

    @Operation(summary = "Reativar usuário organizador com e-mail e senha")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário reativado"
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
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Credenciais inválidas",
                    content = @Content(
                            schema = @Schema(implementation = ExceptioDTO.class),
                            examples = @ExampleObject(
                                    name = "Credenciais inválidas",
                                    value = "{\"status\": 409, \"mensagem\": \"Credenciais inválidas\"}"
                            )
                    )
            )
    })
    @PatchMapping("/reativar")
    ResponseEntity<Void> reativar(@RequestBody ReativarUsuarioOrganizadorRequestDTO dto);
}
