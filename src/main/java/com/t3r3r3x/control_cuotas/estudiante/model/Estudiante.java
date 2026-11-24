package com.t3r3r3x.control_cuotas.estudiante.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    private Boolean activo;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime creadoEn;

    private LocalDateTime actualizadoEn;

    @PrePersist
    public void establecerValoresIniciales() {
        this.creadoEn = LocalDateTime.now();
        this.activo = Boolean.TRUE;
    }

    @PreUpdate
    public void actualizarFechaDeModificacion() {
        this.actualizadoEn = LocalDateTime.now();
    }

    public void desactivar() {
        this.activo = Boolean.FALSE;
    }

    public void activar() {
        this.activo = Boolean.TRUE;
    }
}