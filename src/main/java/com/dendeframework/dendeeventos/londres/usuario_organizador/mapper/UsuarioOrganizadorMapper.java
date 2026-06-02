package com.dendeframework.dendeeventos.londres.usuario_organizador.mapper;

import com.dendeframework.dendeeventos.londres.empresa.dto.EmpresaDTO;
import com.dendeframework.dendeeventos.londres.empresa.model.Empresa;
import com.dendeframework.dendeeventos.londres.usuario.model.TipoUsuario;
import com.dendeframework.dendeeventos.londres.usuario.model.UsuarioOrganizador;
import com.dendeframework.dendeeventos.londres.usuario_organizador.dto.UsuarioOrganizadorDTO;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioOrganizadorMapper {

    public UsuarioOrganizadorDTO toDTO(UsuarioOrganizador entity) {
        return UsuarioOrganizadorDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .sexo(entity.getSexo())
                .idade(this.calcularIdade(entity.getDataNascimento()))
                .isAtivo(entity.getIsAtivo())
                .tipoUsuario(TipoUsuario.ORGANIZADOR)
                .empresa(this.toEmpresaDTO(entity.getEmpresa()))
                .build();
    }

    public UsuarioOrganizador toEntity(UsuarioOrganizadorDTO dto) {

        if (dto == null) {
            return null;
        }

        UsuarioOrganizador entity = new UsuarioOrganizador();

        entity.setId(dto.id());
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setIsAtivo(dto.isAtivo());
        entity.setSexo(dto.sexo());
        entity.setEmpresa(this.toEmpresaEntity(dto.empresa()));

        return entity;
    }

    public Empresa toEmpresaEntity(EmpresaDTO dto) {
        if (dto == null) {
            return null;
        }

        Empresa empresa = new Empresa();
        empresa.setCnpj(dto.cnpj());
        empresa.setRazaoSocial(dto.razaoSocial());
        empresa.setNomeFantasia(dto.nomeFantasia());

        return empresa;
    }

    private EmpresaDTO toEmpresaDTO(Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        return EmpresaDTO.builder()
                .id(empresa.getId())
                .cnpj(empresa.getCnpj())
                .razaoSocial(empresa.getRazaoSocial())
                .nomeFantasia(empresa.getNomeFantasia())
                .build();
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
