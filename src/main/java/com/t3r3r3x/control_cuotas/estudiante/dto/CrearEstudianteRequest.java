package com.t3r3r3x.control_cuotas.estudiante.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CrearEstudianteRequest {
    private String nombre;
    private String apellido;
    private String dni;
}
