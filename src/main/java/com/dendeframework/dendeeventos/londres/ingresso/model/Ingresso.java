package com.dendeframework.dendeeventos.londres.ingresso.model;

import com.dendeframework.dendeeventos.londres.evento.model.Evento;
import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioComum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ingresso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_pago", nullable = false)
    private Double valorPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private StatusIngresso status;

    @Builder.Default
    @Column(name = "valor_estornado", nullable = false)
    private Double valorEstornado = 0.0;

    @Column(name = "data_compra", nullable = false)
    private LocalDateTime dataCompra;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioComum usuarioComum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;
}
