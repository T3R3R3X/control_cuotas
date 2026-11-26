package com.t3r3r3x.control_cuotas.estudiante.service;

import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ServicioEstudiantes {
    Page<Estudiante> listarEstudiantesPaginados(int numeroPagina, int tamanioPagina, boolean activo);

    Estudiante obtenerEstudiantePorId(UUID id);

    Estudiante crearEstudiante(Estudiante estudiante);

    Estudiante actualizarEstudiante(UUID id, Estudiante estudiante);

    Estudiante desactivarEstudiante(UUID id);

    Estudiante activarEstudiante(UUID id);
}
