package com.t3r3r3x.control_cuotas.categoria.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CategoriaResponse {
    private Integer id;
    private String nombre;
}
