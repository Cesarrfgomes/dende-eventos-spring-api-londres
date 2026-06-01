package com.dendeframework.dendeeventos.londres.evento.model;

import com.dendeframework.dendeeventos.londres.exception.DataInicioMaiorQueDataFimException;
import com.dendeframework.dendeeventos.londres.exception.IntervaloMinimoNaoAtingidoException;
import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioOrganizador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "evento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "pagina_web", length = 500)
    private String paginaWeb;

    @Column(name = "tipo_evento", length = 30, nullable = false)
    private TipoEvento tipoEvento;

    @Column(name = "modalidade", length = 10, nullable = false)
    private ModalidadeEvento modalidade;

    @Column(name = "local_evento", length = 500, nullable = false)
    private String localEvento;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;

    @Column(name = "capacidade_maxima", nullable = false)
    private Integer capacidadeMaxima;

    @Column(name = "preco_ingresso", nullable = false)
    private Double precoIngresso;

    @Column(name = "estorna_ingresso", nullable = false)
    private Boolean estornaIngresso;

    @Column(name = "taxa_estorno", nullable = false)
    private Double taxaEstorno;

    @Column(name = "ativo", nullable = false)
    private Boolean isAtivo;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizador_id", nullable = false)
    private UsuarioOrganizador organizador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_principal_id")
    private Evento eventoPrincipal;

    @OneToMany(mappedBy = "eventoPrincipal")
    private List<Evento> subEventos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDateTime.now();
        }

        if (precoIngresso < 0.0) {
            precoIngresso = 0.0;
        }

        if (taxaEstorno < 0.0) {
            taxaEstorno = 0.0;
        }

        if (dataInicio.isAfter(dataFim)) {
            throw new DataInicioMaiorQueDataFimException("Data de inicio não pode ser maio que a data final do evento");
        }

        if (ChronoUnit.MINUTES.between(dataInicio, dataFim) <= 30) {
            throw new IntervaloMinimoNaoAtingidoException("O intervalo mínimo de 30 minutos não foi atingido na duração do evento.");
        }

    }
}
