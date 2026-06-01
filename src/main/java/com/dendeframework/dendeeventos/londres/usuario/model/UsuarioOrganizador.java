package com.dendeframework.dendeeventos.londres.usuario.model;

import com.dendeframework.dendeeventos.londres.empresa.model.Empresa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ORGANIZADOR")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UsuarioOrganizador extends Usuario {

    @OneToOne(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Empresa empresa;

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        if (empresa != null) {
            empresa.setOrganizador(this);
        }
    }
}