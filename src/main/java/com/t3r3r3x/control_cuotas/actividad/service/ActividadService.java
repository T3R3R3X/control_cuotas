package com.t3r3r3x.control_cuotas.actividad.service;

import com.t3r3r3x.control_cuotas.actividad.exception.ActividadNoEncontradaException;
import com.t3r3r3x.control_cuotas.actividad.exception.NombreActividadRegistradaException;
import com.t3r3r3x.control_cuotas.actividad.model.Actividad;
import com.t3r3r3x.control_cuotas.actividad.repository.ActividadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActividadService implements ServicioActividades {

    private final ActividadRepository actividadRepository;

    @Override
    public List<Actividad> obtenerTodasLasActividades() {
        return actividadRepository.findAll();
    }

    @Override
    public Actividad obtenerUnaActividadPorId(Integer id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new ActividadNoEncontradaException("Actividad no encontrada"));
    }

    @Override
    public Actividad crearActividad(Actividad actividad) {

        if (actividadRepository.existsByNombre(actividad.getNombre())) {
            throw new NombreActividadRegistradaException("Nombre de actividad ya registrado");
        }

        return actividadRepository.save(actividad);
    }
}
