package com.dendeframework.dendeeventos.londres.usuario_comum.service;

import com.dendeframework.dendeeventos.londres.exception.ConflitoException;
import com.dendeframework.dendeeventos.londres.exception.RecursoNaoEncontradoException;
import com.dendeframework.dendeeventos.londres.usuario.infra.UsuarioRepository;
import com.dendeframework.dendeeventos.londres.usuario.model.TipoUsuario;
import com.dendeframework.dendeeventos.londres.usuario.model.Usuario;
import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioComum;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.AtualizarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.CriarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.ReativarUsuarioComumRequestDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.UsuarioComumDTO;
import com.dendeframework.dendeeventos.londres.usuario_comum.mapper.UsuarioComumMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioComumService {

    private final UsuarioRepository repository;

    private final UsuarioComumMapper usuarioComumMapper;

    public UsuarioComumService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
        this.usuarioComumMapper = new UsuarioComumMapper();
    }

    @Transactional
    public UsuarioComumDTO criarUsuarioComum(CriarUsuarioComumRequestDTO dto) {
        Optional<Usuario> usuario = this.repository.findByEmail(dto.email());

        if (usuario.isPresent()) {
            throw new ConflitoException("Email já cadastrado");
        }

        UsuarioComum usuarioComum = new UsuarioComum();
        BeanUtils.copyProperties(dto, usuarioComum);

        UsuarioComum novoUsuario = this.repository.save(usuarioComum);

        return this.usuarioComumMapper.toDTO(novoUsuario);
    }

    @Transactional
    public void atualizarUsuarioComum(Long id, AtualizarUsuarioComumRequestDTO dto) {
        UsuarioComum usuario = this.buscarUsuarioComumPorId(id);
        usuario.setNome(dto.nome());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setSexo(dto.sexo());
        this.repository.save(usuario);
    }

    public UsuarioComumDTO getById(Long id) {
        return this.usuarioComumMapper.toDTO(this.buscarUsuarioComumPorId(id));
    }

    public UsuarioComumDTO getByEmail(String email) {
        UsuarioComum usuario = (UsuarioComum) this.repository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        return this.usuarioComumMapper.toDTO(usuario);
    }

    @Transactional
    public void desativarUsuarioComum(Long id) {
        UsuarioComum usuario = this.buscarUsuarioComumPorId(id);
        usuario.setIsAtivo(false);
        this.repository.save(usuario);
    }

    @Transactional
    public void reativarUsuarioComum(ReativarUsuarioComumRequestDTO dto) {
        UsuarioComum usuario = (UsuarioComum) repository.findByEmail(dto.email())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(dto.senha())) {
            throw new ConflitoException("Credenciais inválidas");
        }

        usuario.setIsAtivo(true);
        this.repository.save(usuario);
    }

    private UsuarioComum buscarUsuarioComumPorId(Long id) {
        return (UsuarioComum) this.repository.findByIdAndTipoUsuario(id, TipoUsuario.COMUM.name())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }
}
