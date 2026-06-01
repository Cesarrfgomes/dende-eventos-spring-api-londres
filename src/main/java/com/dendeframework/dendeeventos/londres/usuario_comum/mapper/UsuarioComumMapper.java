package com.dendeframework.dendeeventos.londres.usuario_comum.mapper;

import com.dendeframework.dendeeventos.londres.usuario.model.TipoUsuario;
import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioComum;
import com.dendeframework.dendeeventos.londres.usuario_comum.dto.UsuarioComumDTO;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioComumMapper {

    public UsuarioComumDTO toDTO(UsuarioComum entity) {
        return UsuarioComumDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .sexo(entity.getSexo())
                .idade(this.calcularIdade(entity.getDataNascimento()))
                .isAtivo(entity.getIsAtivo())
                .tipoUsuario(TipoUsuario.COMUM)
                .build();
    }

    public UsuarioComum toEntity(UsuarioComumDTO dto) {

        if (dto == null) {
            return null;
        }

        UsuarioComum entity = new UsuarioComum();

        entity.setId(dto.id());
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setIsAtivo(dto.isAtivo());
        entity.setSexo(dto.sexo());

        return entity;
    }

    private String calcularIdade(LocalDate nascimento) {
        if (nascimento == null) {
            return null;
        }

        Period periodo = Period.between(nascimento, LocalDate.now());

        return periodo.getYears() + " anos, "
                + periodo.getMonths() + " meses e "
                + periodo.getDays() + " dias";
    }
}
