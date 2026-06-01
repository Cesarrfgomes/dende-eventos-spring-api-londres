package com.dendeframework.dendeeventos.londres.usuario_comum.api;

import com.dendeframework.dendeeventos.londres.usuario_comum.dto.AtualizarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.CriarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.ReativarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.UsuarioComumDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.service.UsuarioComumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class UsuarioComumControllerImpl implements UsuarioComumController {

    private final UsuarioComumService service;

    public UsuarioComumControllerImpl(UsuarioComumService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<UsuarioComumDTO> criar(CriarUsuarioComumRequestDTO dto) {
        UsuarioComumDTO criado = this.service.criarUsuarioComum(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @Override
    public ResponseEntity<UsuarioComumDTO> buscarPorId(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<UsuarioComumDTO> buscarPorEmail(String email) {
        return ResponseEntity.ok(this.service.getByEmail(email));
    }

    @Override
    public ResponseEntity<Void> atualizar(Long id, AtualizarUsuarioComumRequestDTO dto) {
        this.service.atualizarUsuarioComum(id, dto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> desativar(Long id) {
        this.service.desativarUsuarioComum(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> reativar(ReativarUsuarioComumRequestDTO dto) {
        this.service.reativarUsuarioComum(dto);
        return ResponseEntity.noContent().build();
    }
}
