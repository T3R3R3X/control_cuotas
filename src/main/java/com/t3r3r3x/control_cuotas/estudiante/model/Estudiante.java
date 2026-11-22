package com.t3r3r3x.control_cuotas.estudiante.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estudiante {
    @EqualsAndHashCode.Include
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Boolean activo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
}
