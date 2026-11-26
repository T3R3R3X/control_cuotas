package com.t3r3r3x.control_cuotas.estudiante.service;

import com.t3r3r3x.control_cuotas.estudiante.exception.DniRegistradoException;
import com.t3r3r3x.control_cuotas.estudiante.exception.EstudianteNoEncontradoException;
import com.t3r3r3x.control_cuotas.estudiante.exception.ParametrosPaginacionInvalidosException;
import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import com.t3r3r3x.control_cuotas.estudiante.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EstudianteService implements ServicioEstudiantes {

    private final EstudianteRepository estudianteRepository;

    @Override
    public Page<Estudiante> listarEstudiantesPaginados(int numeroPagina, int tamanioPagina, boolean activo) {

        if (numeroPagina < 0 || tamanioPagina <= 0) {
            throw new ParametrosPaginacionInvalidosException(
                    "La página debe ser mayor o igual a 0 y el tamaño debe ser mayor a 0"
            );
        }

        Pageable pageable = PageRequest.of(numeroPagina, tamanioPagina);

        return estudianteRepository.findAllByActivo(pageable, activo);
    }

    @Override
    public Estudiante obtenerEstudiantePorId(UUID id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException("Estudiante no encontrado"));
    }

    @Transactional
    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) {

        if (estudianteRepository.existsByDni(estudiante.getDni())) {
            throw new DniRegistradoException("El DNI ya está registrado");
        }

        return estudianteRepository.save(estudiante);
    }


    @Transactional
    @Override
    public Estudiante actualizarEstudiante(UUID id, Estudiante estudiante) {

        Estudiante estudianteExistente = obtenerEstudiantePorId(id);

        // valido que otro estudiante no tenga el mismo DNI
        if (estudianteRepository.existsByDniAndIdNot(estudiante.getDni(), id)) {
            throw new DniRegistradoException("El DNI ya está registrado");
        }

        estudianteExistente.setNombre(estudiante.getNombre());
        estudianteExistente.setApellido(estudiante.getApellido());
        estudianteExistente.setDni(estudiante.getDni());

        return estudianteRepository.save(estudianteExistente);
    }

    @Transactional
    @Override
    public Estudiante desactivarEstudiante(UUID id) {

        Estudiante estudiante = obtenerEstudiantePorId(id);

        estudiante.desactivar();

        return estudianteRepository.save(estudiante);
    }

    @Transactional
    @Override
    public Estudiante activarEstudiante(UUID id) {

        Estudiante estudiante = obtenerEstudiantePorId(id);

        estudiante.activar();

        return estudianteRepository.save(estudiante);
    }
}
