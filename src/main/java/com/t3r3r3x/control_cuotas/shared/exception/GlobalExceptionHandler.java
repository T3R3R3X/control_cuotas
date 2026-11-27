package com.t3r3r3x.control_cuotas.shared.exception;

import com.t3r3r3x.control_cuotas.actividad.exception.ActividadNoEncontradaException;
import com.t3r3r3x.control_cuotas.actividad.exception.NombreActividadRegistradaException;
import com.t3r3r3x.control_cuotas.categoria.exception.CategoriaNoEncontradaException;
import com.t3r3r3x.control_cuotas.categoria.exception.NombreCategoriaRegistradaException;
import com.t3r3r3x.control_cuotas.estudiante.exception.EstudianteNoEncontradoException;
import com.t3r3r3x.control_cuotas.estudiante.exception.DniRegistradoException;
import com.t3r3r3x.control_cuotas.estudiante.exception.ParametrosPaginacionInvalidosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail manejarExcepcionNoControlada() {

        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor"
        );

        problema.setTitle("Error interno");

        return problema;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail manejarErroresDeValidacion(MethodArgumentNotValidException ex) {

        ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problema.setTitle("Error de validación");

        List<String> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errores.add(error.getField() + ": " + error.getDefaultMessage()));

        ex.getBindingResult().getGlobalErrors()
                .forEach(error -> errores.add(error.getDefaultMessage()));

        problema.setProperty("errores", errores);

        return problema;
    }

    @ExceptionHandler({
            HandlerMethodValidationException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    public ProblemDetail manejarSolicitudInvalida(Exception ex) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "La solicitud contiene parámetros o datos inválidos"
        );
        problema.setTitle("Solicitud inválida");

        return problema;
    }

    @ExceptionHandler(EstudianteNoEncontradoException.class)
    public ProblemDetail manejarEstudianteNoEncontrado(EstudianteNoEncontradoException ex) {

        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problema.setTitle("Estudiante no encontrado");

        return problema;
    }

    @ExceptionHandler(DniRegistradoException.class)
    public ProblemDetail manejarDniDuplicado(DniRegistradoException ex) {

        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );
        problema.setTitle("DNI ya registrado");

        return problema;
    }

    @ExceptionHandler(ParametrosPaginacionInvalidosException.class)
    public ProblemDetail manejarPaginacionInvalida(ParametrosPaginacionInvalidosException ex) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage()
        );
        problema.setTitle("Parámetros de paginación inválidos");

        return problema;
    }

    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ProblemDetail manejarCategoriaNoEncontrada(CategoriaNoEncontradaException ex) {

        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problema.setTitle("Categoría no encontrada");

        return problema;
    }

    @ExceptionHandler(NombreCategoriaRegistradaException.class)
    public ProblemDetail manejarNombreCategoriaRegistrada(NombreCategoriaRegistradaException ex) {

        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );
        problema.setTitle("Nombre de categoría ya registrado");

        return problema;
    }

    @ExceptionHandler(ActividadNoEncontradaException.class)
    public ProblemDetail manejarActividadNoEncontrada(ActividadNoEncontradaException ex) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problema.setTitle("Actividad no encontrada");

        return problema;
    }

    @ExceptionHandler(NombreActividadRegistradaException.class)
    public ProblemDetail manejarNombreActividadRegistrado(NombreActividadRegistradaException ex) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );
        problema.setTitle("Nombre de actividad ya registrado");

        return problema;
    }
}
