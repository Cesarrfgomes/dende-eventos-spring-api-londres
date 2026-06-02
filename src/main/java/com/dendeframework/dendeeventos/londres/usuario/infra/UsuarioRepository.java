package com.dendeframework.dendeeventos.londres.usuario.infra;

import com.dendeframework.dendeeventos.londres.usuario.model.TipoUsuario;
import com.dendeframework.dendeeventos.londres.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = """
        SELECT * FROM usuario WHERE id = :id AND tipo_usuario = :tipoUsuario;
    """, nativeQuery = true)
    Optional<Usuario> findByIdAndTipoUsuario(Long id, String tipoUsuario);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}