package com.dendeframework.dendeeventos.londres.ingresso.mapper;

import com.dendeframework.dendeeventos.londres.ingresso.dto.IngressoDTO;
import com.dendeframework.dendeeventos.londres.ingresso.model.Ingresso;

public class IngressoMapper {

    public IngressoDTO toDTO(Ingresso entity) {
        if (entity == null) {
            return null;
        }

        return IngressoDTO.builder()
                .id(entity.getId())
                .valorPago(entity.getValorPago())
                .status(entity.getStatus())
                .valorEstornado(entity.getValorEstornado())
                .dataCompra(entity.getDataCompra())
                .dataCancelamento(entity.getDataCancelamento())
                .usuarioComumId(entity.getUsuarioComum() != null ? entity.getUsuarioComum().getId() : null)
                .eventoId(entity.getEvento() != null ? entity.getEvento().getId() : null)
                .eventoNome(entity.getEvento() != null ? entity.getEvento().getNome() : null)
                .build();
    }
}