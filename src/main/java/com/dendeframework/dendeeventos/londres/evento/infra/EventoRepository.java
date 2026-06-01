package com.dendeframework.dendeeventos.londres.evento.infra;

import com.dendeframework.dendeeventos.londres.evento.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByOrganizadorId(Long id);
}
