package com.dendeframework.dendeeventos.londres.ingresso.infra;

import com.dendeframework.dendeeventos.londres.ingresso.model.Ingresso;
import com.dendeframework.dendeeventos.londres.ingresso.model.StatusIngresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    long countByEventoIdAndStatus(Long eventoId, StatusIngresso status);

    @Query(value = """
        SELECT i.* FROM ingresso i, evento e
        WHERE e.id = i.evento_id
          AND i.usuario_id = :usuarioComumId
        ORDER BY
            CASE WHEN i.status = 'ATIVO' AND e.ativo = TRUE AND e.data_fim > NOW() THEN 0 ELSE 1 END ASC,
            e.data_inicio ASC,
            e.nome ASC
    """, nativeQuery = true)
    List<Ingresso> findIngressosDoUsuarioOrdenados(@Param("usuarioComumId") Long usuarioComumId);
}
