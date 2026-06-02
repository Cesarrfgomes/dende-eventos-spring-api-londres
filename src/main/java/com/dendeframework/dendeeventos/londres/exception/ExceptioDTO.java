package com.dendeframework.dendeeventos.londres.exception;

import lombok.Builder;

@Builder
public record ExceptioDTO(
        Integer status,
        String mensagem
) {
}
