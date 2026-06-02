package com.dendeframework.dendeeventos.londres.ingresso.api;

import com.dendeframework.dendeeventos.londres.ingresso.dto.CompraIngressoDTO;
import com.dendeframework.dendeeventos.londres.ingresso.dto.ComprarIngressoRequestDTO;
import com.dendeframework.dendeeventos.londres.ingresso.dto.IngressoDTO;
import com.dendeframework.dendeeventos.londres.ingresso.service.IngressoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngressoControllerImpl implements IngressoController {

    private final IngressoService service;

    public IngressoControllerImpl(IngressoService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CompraIngressoDTO> comprarIngresso(ComprarIngressoRequestDTO dto) {
        CompraIngressoDTO compra = this.service.comprarIngresso(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(compra);
    }

    @Override
    public ResponseEntity<List<IngressoDTO>> listarIngressosDoUsuario(Long usuarioComumId) {
        List<IngressoDTO> ingressos = this.service.listarIngressosDoUsuario(usuarioComumId);

        return ResponseEntity.ok(ingressos);
    }
}