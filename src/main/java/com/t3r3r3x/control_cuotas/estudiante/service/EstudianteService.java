package com.t3r3r3x.control_cuotas.estudiante.service;

import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {

    @Override
    public List<Estudiante> obtenerTodo() {
        //
        return Collections.emptyList();
    }

    @Override
    public Estudiante crear(Estudiante estudiante) {
        return null;
    }

    @Override
    public Estudiante actualizar(Long id, Estudiante estudiante) {
        return null;
    }

    @Override
    public Estudiante eliminar(Long id) {
        return null;
    }
}
