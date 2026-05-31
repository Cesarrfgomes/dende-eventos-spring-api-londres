package com.dendeframework.dendeeventos.londres.usuario.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("COMUM")
@NoArgsConstructor
@Builder
public class UsuarioComum extends Usuario {
}