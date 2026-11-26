package com.t3r3r3x.control_cuotas.estudiante.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EstudianteResponse {
    private UUID id;
    private String nombre;
    private String apellido;
    private String dni;
    private Boolean activo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
}
