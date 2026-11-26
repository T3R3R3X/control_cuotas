package com.t3r3r3x.control_cuotas.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GuardarCategoriaRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
