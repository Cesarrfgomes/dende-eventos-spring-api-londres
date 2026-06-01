package com.dendeframework.dendeeventos.londres.empresa.infra;

import com.dendeframework.dendeeventos.londres.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
