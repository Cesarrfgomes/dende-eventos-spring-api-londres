package com.dendeframework.dendeeventos.londres.usuario_organizador.api;

import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.AtualizarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.CriarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.ReativarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.UsuarioOrganizadorDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.service.UsuarioOrganizadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioOrganizadorControllerImpl implements UsuarioOrganizadorController {

    private final UsuarioOrganizadorService service;

    public UsuarioOrganizadorControllerImpl(UsuarioOrganizadorService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<UsuarioOrganizadorDTO> criar(CriarUsuarioOrganizadorRequestDTO dto) {
        UsuarioOrganizadorDTO novoUsuario = this.service.criarUsuarioOrganizador(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @Override
    public ResponseEntity<UsuarioOrganizadorDTO> buscarPorId(Long id) {
        UsuarioOrganizadorDTO usuario = this.service.getById(id);

        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<Void> atualizar(Long id, AtualizarUsuarioOrganizadorRequestDTO dto) {
        this.service.atualizarUsuarioOrganizador(id, dto);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> desativar(Long id) {
        this.service.desativarUsuarioOrganizador(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> reativar(ReativarUsuarioOrganizadorRequestDTO dto) {
        this.service.reativarUsuarioOrganizador(dto);

        return ResponseEntity.noContent().build();
    }
}
