package com.t3r3r3x.control_cuotas.actividad.service;

import com.t3r3r3x.control_cuotas.actividad.model.Actividad;

import java.util.List;

public interface ServicioActividades {

    List<Actividad> obtenerTodasLasActividades();

    Actividad obtenerUnaActividadPorId(Integer id);

    Actividad crearActividad(Actividad actividad);
}
