package com.dendeframework.dendeeventos.londres.empresa.model;

import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioOrganizador;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@Data
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj", length = 18, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organizador_id", referencedColumnName = "id", unique = true, nullable = false)
    private UsuarioOrganizador organizador;
}
