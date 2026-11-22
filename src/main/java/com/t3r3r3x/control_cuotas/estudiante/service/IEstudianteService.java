package com.t3r3r3x.control_cuotas.estudiante.service;

import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;

import java.util.List;

public interface IEstudianteService {
    List<Estudiante> obtenerTodo();

    Estudiante crear(Estudiante estudiante);

    Estudiante actualizar(Long id, Estudiante estudiante);

    Estudiante eliminar(Long id);
}
