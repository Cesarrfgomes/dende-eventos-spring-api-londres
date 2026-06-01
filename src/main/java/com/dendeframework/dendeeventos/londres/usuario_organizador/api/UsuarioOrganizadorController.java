package com.dendeframework.dendeeventos.londres.usuario_organizador.api;

import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.CriarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.UsuarioOrganizadorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Usuário Organizador", description = "Operações de cadastro e gestão de usuários organizadores")
@RequestMapping("/usuarios/organizadores")
public interface UsuarioOrganizadorController {
    @Operation(summary = "Cadastrar usuário organizador")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    @PostMapping
    ResponseEntity<UsuarioOrganizadorDTO> criar(@RequestBody CriarUsuarioOrganizadorRequestDTO dto);
}
