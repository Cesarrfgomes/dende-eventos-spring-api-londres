package com.dendeframework.dendeeventos.londres.evento.infra;

import com.dendeframework.dendeeventos.londres.evento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByOrganizadorId(Long id);
    boolean existsByOrganizadorIdAndIsAtivoTrue(Long organizadorId);
    Optional<Evento> findByIdAndOrganizadorId(Long id, Long organizadorId);

    @Query(value = """
        SELECT e.* FROM evento e
        WHERE e.ativo = TRUE
          AND e.data_inicio > NOW()
          AND (
              SELECT COUNT(*) FROM ingresso i
              WHERE i.evento_id = e.id
                AND i.status = 'ATIVO'
          ) < e.capacidade_maxima
        ORDER BY
            e.data_inicio ASC,
            e.nome ASC
    """, nativeQuery = true)
    List<Evento> findFeedEventosAtivos();
}
