package com.dendeframework.dendeeventos.londres.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<Object> conflito(ConflitoException e) {
        ExceptioDTO exceptioDTO = ExceptioDTO.builder()
                .status(HttpStatus.CONFLICT.value())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptioDTO);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ExceptioDTO> naoEncontrado(RecursoNaoEncontradoException e) {
        ExceptioDTO exceptioDTO = ExceptioDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptioDTO);
    }


    @ExceptionHandler(DataInicioMaiorQueDataFimException.class)
    public ResponseEntity<Object> dataInicio(DataInicioMaiorQueDataFimException e) {
        ExceptioDTO exceptioDTO = ExceptioDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(exceptioDTO);
    }

    @ExceptionHandler(IntervaloMinimoNaoAtingidoException.class)
    public ResponseEntity<Object> intervaloMinimo(IntervaloMinimoNaoAtingidoException e) {
        ExceptioDTO exceptioDTO = ExceptioDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(exceptioDTO);
    }

    @ExceptionHandler(OrganizadorComEventosAtivosException.class)
    public ResponseEntity<Object> organizadorComEventosAtivos(OrganizadorComEventosAtivosException e) {
        ExceptioDTO exceptioDTO = ExceptioDTO.builder()
                .status(HttpStatus.CONFLICT.value())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptioDTO);
    }
}
