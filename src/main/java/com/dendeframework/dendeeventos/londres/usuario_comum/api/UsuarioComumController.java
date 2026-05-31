package com.dendeframework.dendeeventos.londres.usuario_comum.api;

import com.dendeframework.dendeeventos.londres.usuario_comum.dto.AtualizarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.CriarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.ReativarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.UsuarioComumDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuário Comum", description = "Operações de cadastro e gestão de usuários comuns")
@RequestMapping("/usuarios/comuns")
public interface UsuarioComumController {

    @Operation(summary = "Cadastrar usuário comum")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    @PostMapping
    ResponseEntity<UsuarioComumDTO> criar(@RequestBody CriarUsuarioComumRequestDTO dto);

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    ResponseEntity<UsuarioComumDTO> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Buscar usuário por e-mail")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/email/{email}")
    ResponseEntity<UsuarioComumDTO> buscarPorEmail(@PathVariable String email);

    @Operation(summary = "Atualizar dados cadastrais")
    @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PutMapping("/{id}")
    ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AtualizarUsuarioComumRequestDTO dto);

    @Operation(summary = "Desativar usuário")
    @ApiResponse(responseCode = "204", description = "Usuário desativado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> desativar(@PathVariable Long id);

    @Operation(summary = "Reativar usuário com e-mail e senha")
    @ApiResponse(responseCode = "204", description = "Usuário reativado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "409", description = "Credenciais inválidas")
    @PatchMapping("/reativar")
    ResponseEntity<Void> reativar(@RequestBody ReativarUsuarioComumRequestDTO dto);
}
