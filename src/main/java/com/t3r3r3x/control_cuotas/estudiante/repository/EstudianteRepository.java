package com.t3r3r3x.control_cuotas.estudiante.repository;

import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, UUID> {

    Page<Estudiante> findAllByActivo(Boolean activo, Pageable pageable);

    boolean existsByDni(String dni);
}
