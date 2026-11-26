package com.t3r3r3x.control_cuotas.estudiante.service;

import com.t3r3r3x.control_cuotas.estudiante.exception.DniRegistradoException;
import com.t3r3r3x.control_cuotas.estudiante.exception.EstudianteNoEncontradoException;
import com.t3r3r3x.control_cuotas.estudiante.exception.ParametrosPaginacionInvalidosException;
import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import com.t3r3r3x.control_cuotas.estudiante.repository.EstudianteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @InjectMocks
    private EstudianteService estudianteService;

    @Mock
    private EstudianteRepository estudianteRepository;

    @Test
    void deberiaLanzarExcepcionSiNoEncuentraAlEstudiante() {

        var excepcion = assertThrows(
                EstudianteNoEncontradoException.class,
                () -> estudianteService.obtenerEstudiantePorId(UUID.randomUUID())
        );

        verify(estudianteRepository).findById(Mockito.any(UUID.class));

        assertEquals("Estudiante no encontrado", excepcion.getMessage());
    }

    @Test
    void deberiaLanzarExcepcionSiElNumeroDePaginaEsNegativo() {

        var excepcion = assertThrows(ParametrosPaginacionInvalidosException.class, () -> {
            estudianteService.listarEstudiantesPaginados(-1, 10, true);
        });

        assertEquals("La página debe ser mayor o igual a 0 y el tamaño debe ser mayor a 0", excepcion.getMessage());
    }

    @Test
    void deberiaLanzarExcepcionSiElTamanioEsCero() {

        var excepcion = assertThrows(ParametrosPaginacionInvalidosException.class, () -> {
            estudianteService.listarEstudiantesPaginados(1, 0, true);
        });

        assertEquals("La página debe ser mayor o igual a 0 y el tamaño debe ser mayor a 0", excepcion.getMessage());
    }

    @Test
    void deberiaListarEstudiantesPaginados() {

        Mockito.when(estudianteRepository.findAllByActivo(Mockito.any(Pageable.class), Mockito.anyBoolean())).thenReturn(Page.empty());

        var resultado = estudianteService.listarEstudiantesPaginados(1, 1, true);

        assertEquals(Page.empty(), resultado);
    }

    @Test
    void deberiaObtenerEstudiantePorId() {

        var estudiante = new Estudiante();
        estudiante.setId(UUID.randomUUID());

        Mockito.when(estudianteRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(estudiante));

        var resultado = estudianteService.obtenerEstudiantePorId(estudiante.getId());

        verify(estudianteRepository).findById(Mockito.any(UUID.class));

        assertEquals(estudiante, resultado);
    }

    @Test
    void deberiaLanzarExcepcionSiElDniYaEstaRegistrado() {

        var estudiante = new Estudiante();
        estudiante.setDni("12345678");

        Mockito.when(estudianteRepository.existsByDni(Mockito.anyString())).thenReturn(true);

        var excepcion = assertThrows(DniRegistradoException.class, () -> {
            estudianteService.crearEstudiante(estudiante);
        });

        assertEquals("El DNI ya está registrado", excepcion.getMessage());
    }

    @Test
    void deberiaCrearUnNuevoEstudiante() {

        var nuevoEstudiante = new Estudiante();
        nuevoEstudiante.setNombre("Juan");
        nuevoEstudiante.setApellido("Pérez");
        nuevoEstudiante.setDni("12345678");

        Mockito.when(estudianteRepository.existsByDni(Mockito.anyString())).thenReturn(false);
        Mockito.when(estudianteRepository.save(Mockito.any(Estudiante.class))).thenReturn(nuevoEstudiante);

        var resultado = estudianteService.crearEstudiante(nuevoEstudiante);

        assertEquals(nuevoEstudiante, resultado);
    }

    @Test
    void deberiaActualizarUnEstudiante() {

        var estudianteExistente = new Estudiante();
        estudianteExistente.setId(UUID.randomUUID());
        estudianteExistente.setNombre("Juan");
        estudianteExistente.setApellido("Pérez");
        estudianteExistente.setDni("12345678");

        var nuevosDatos = new Estudiante();
        nuevosDatos.setDni("87654321");

        Mockito.when(estudianteRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(estudianteExistente));
        Mockito.when(estudianteRepository.save(Mockito.any(Estudiante.class))).thenReturn(estudianteExistente);

        var resultado = estudianteService.actualizarEstudiante(estudianteExistente.getId(), nuevosDatos);

        assertEquals("87654321", resultado.getDni());
    }

    @Test
    void deberiaDesactivarUnEstudiante() {

        var estudianteActivo = new Estudiante();
        estudianteActivo.setId(UUID.randomUUID());
        estudianteActivo.setActivo(Boolean.TRUE);

        Mockito.when(estudianteRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(estudianteActivo));
        Mockito.when(estudianteRepository.save(Mockito.any(Estudiante.class))).thenReturn(estudianteActivo);

        var resultado = estudianteService.desactivarEstudiante(estudianteActivo.getId());

        assertFalse(resultado.getActivo());
    }

    @Test
    void deberiaActivarUnEstudiante() {

        var estudianteInactivo = new Estudiante();
        estudianteInactivo.setId(UUID.randomUUID());
        estudianteInactivo.setActivo(Boolean.FALSE);

        Mockito.when(estudianteRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(estudianteInactivo));
        Mockito.when(estudianteRepository.save(Mockito.any(Estudiante.class))).thenReturn(estudianteInactivo);

        var resultado = estudianteService.activarEstudiante(estudianteInactivo.getId());

        assertTrue(resultado.getActivo());
    }
}