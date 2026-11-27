package com.t3r3r3x.control_cuotas.actividad.repository;

import com.t3r3r3x.control_cuotas.actividad.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

    boolean existsByNombre(String nombre);
}
