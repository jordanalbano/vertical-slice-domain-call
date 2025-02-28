package com.jordan.albano.verticalslice.shared;

import com.jordan.albano.verticalslice.exceptions.BadRequestException;
import com.jordan.albano.verticalslice.exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.ws.rs.ForbiddenException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.*;

@Log4j2
@RestControllerAdvice
@Hidden
public class ExceptionController {
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<ProblemDetail> sqlException(SQLException sqlException) {
        log.error(sqlException.getMessage());
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Ups, algo paso");
        problemDetail.setDetail("Ocurrio un inconveniente inesperado, si el problema persiste contacte al administrador");
        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ProblemDetail exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Ups, algo paso");
        problemDetail.setDetail("Ocurrio un inconveniente inesperado, si el problema persiste contacte al administrador");
        return problemDetail;
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ProblemDetail exception(ForbiddenException exception) {
        log.error(exception.getMessage(), exception);
        var problemDetail = ProblemDetail.forStatus(FORBIDDEN);
        problemDetail.setTitle("Acceso denegado");
        problemDetail.setDetail("No tiene permisos para acceder a este recurso");
        return problemDetail;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ProblemDetail badRequestException(BadRequestException exception) {
        ProblemDetail errorDTO = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        errorDTO.setTitle("Error en la solicitud");
        errorDTO.setDetail(exception.getMessage());
        return errorDTO;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail notFound(EntityNotFoundException exception) {
        ProblemDetail errorDTO = ProblemDetail.forStatus(NOT_FOUND);
        errorDTO.setTitle("Error en la solicitud");
        errorDTO.setDetail(exception.getMessage());
        return errorDTO;
    }

}
