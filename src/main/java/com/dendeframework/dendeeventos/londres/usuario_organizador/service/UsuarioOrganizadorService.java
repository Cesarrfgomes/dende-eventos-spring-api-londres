package com.dendeframework.dendeeventos.londres.usuario_organizador.service;

import com.dendeframework.dendeeventos.londres.exception.ConflitoException;
import com.dendeframework.dendeeventos.londres.exception.RecursoNaoEncontradoException;
import com.dendeframework.dendeeventos.londres.usuario.infra.UsuarioRepository;
import com.dendeframework.dendeeventos.londres.usuario.model.Usuario;
import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioOrganizador;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.AtualizarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.CriarUsuarioOrganizadorRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.UsuarioOrganizadorDTO;
import com.dendeframework.dendeeventos.londres.usuario_organizador.mapper.UsuarioOrganizadorMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioOrganizadorService {

    private final UsuarioRepository repository;

    private final UsuarioOrganizadorMapper usuarioOrganizadorMapper;

    public UsuarioOrganizadorService(UsuarioRepository repository) {
        this.repository = repository;
        this.usuarioOrganizadorMapper = new UsuarioOrganizadorMapper();
    }

    @Transactional
    public UsuarioOrganizadorDTO criarUsuarioOrganizador(CriarUsuarioOrganizadorRequestDTO dto) {
        Optional<Usuario> usuario = this.repository.findByEmail(dto.email());

        if (usuario.isPresent()) {
            throw new ConflitoException("Email já cadastrado");
        }

        UsuarioOrganizador usuarioOrganizador = new UsuarioOrganizador();
        BeanUtils.copyProperties(dto, usuarioOrganizador, "empresa");

        if (dto.empresa() != null) {
            usuarioOrganizador.setEmpresa(this.usuarioOrganizadorMapper.toEmpresaEntity(dto.empresa()));
        }

        UsuarioOrganizador novoUsuario = this.repository.save(usuarioOrganizador);

        return this.usuarioOrganizadorMapper.toDTO(novoUsuario);
    }

    @Transactional
    public void atulizarUsuarioOrganizador(Long id, AtualizarUsuarioOrganizadorRequestDTO dto) {
        UsuarioOrganizador usuario = this.buscarUsuarioOrganizadorPorId(id);
        usuario.setNome(dto.nome());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setSexo(dto.sexo());
        usuario.setEmpresa(usuarioOrganizadorMapper.toEmpresaEntity(dto.empresa()));
        this.repository.save(usuario);
    }

    public UsuarioOrganizadorDTO getById(Long id) {
        return this.usuarioOrganizadorMapper.toDTO(this.buscarUsuarioOrganizadorPorId(id));
    }

    private UsuarioOrganizador buscarUsuarioOrganizadorPorId(Long id) {
        return (UsuarioOrganizador) this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }


}
