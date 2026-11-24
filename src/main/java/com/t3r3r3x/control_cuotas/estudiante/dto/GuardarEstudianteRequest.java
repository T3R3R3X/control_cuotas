package com.t3r3r3x.control_cuotas.estudiante.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GuardarEstudianteRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "El DNI es obligatorio")
    private String dni;
}
