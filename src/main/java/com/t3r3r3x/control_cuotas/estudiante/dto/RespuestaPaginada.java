package com.t3r3r3x.control_cuotas.estudiante.dto;

import lombok.Data;

import java.util.List;

@Data
public class RespuestaPaginada<T> {
    private List<T> contenido;
    private int numero;
    private int tamanio;
    private long totalElementos;
    private int totalPaginas;
}
