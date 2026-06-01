package com.dendeframework.dendeeventos.londres.usuario.model;

import com.dendeframework.dendeeventos.londres.empresa.model.Empresa;
import com.dendeframework.dendeeventos.londres.evento.model.Evento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ORGANIZADOR")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UsuarioOrganizador extends Usuario {

    @OneToOne(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Empresa empresa;

    @OneToMany(mappedBy = "organizador", fetch = FetchType.LAZY)
    private List<Evento> eventos = new ArrayList<>();

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        if (empresa != null) {
            empresa.setOrganizador(this);
        }
    }
}