package com.t3r3r3x.control_cuotas.actividad.service;

import com.t3r3r3x.control_cuotas.actividad.exception.ActividadNoEncontradaException;
import com.t3r3r3x.control_cuotas.actividad.exception.NombreActividadRegistradaException;
import com.t3r3r3x.control_cuotas.actividad.model.Actividad;
import com.t3r3r3x.control_cuotas.actividad.repository.ActividadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActividadServiceTest {

    @InjectMocks
    private ActividadService actividadService;

    @Mock
    private ActividadRepository actividadRepository;

    private Actividad actividad;

    @BeforeEach
    void setUp() {
        actividad = new Actividad();
        actividad.setId(1);
        actividad.setNombre("Test Actividad");
    }

    @Test
    void deberiaObtenerTodasLasActividades() {

        Mockito.when(actividadRepository.findAll()).thenReturn(Collections.singletonList(actividad));

        var resultado = actividadService.obtenerTodasLasActividades();

        assertEquals(1, resultado.size());
        assertEquals(actividad, resultado.getFirst());
        verify(actividadRepository).findAll();
    }

    @Test
    void deberiaObtenerUnaActividadPorId() {

        Mockito.when(actividadRepository.findById(actividad.getId())).thenReturn(Optional.of(actividad));

        var resultado = actividadService.obtenerUnaActividadPorId(actividad.getId());

        assertEquals(actividad, resultado);
        verify(actividadRepository).findById(actividad.getId());
    }

    @Test
    void deberiaLanzarExcepcionSiNoEncuentraLaActividad() {

        var excepcion = assertThrows(
                ActividadNoEncontradaException.class,
                () -> actividadService.obtenerUnaActividadPorId(999)
        );

        assertEquals("Actividad no encontrada", excepcion.getMessage());
        verify(actividadRepository).findById(999);
    }

    @Test
    void deberiaLanzarExcepcionSiElNombreYaEstaRegistrado() {

        Mockito.when(actividadRepository.existsByNombre(actividad.getNombre())).thenReturn(true);

        var excepcion = assertThrows(
                NombreActividadRegistradaException.class,
                () -> actividadService.crearActividad(actividad)
        );

        assertEquals("Nombre de actividad ya registrado", excepcion.getMessage());
        verify(actividadRepository).existsByNombre(actividad.getNombre());
    }

    @Test
    void deberiaCrearUnaNuevaActividad() {

        Mockito.when(actividadRepository.existsByNombre(actividad.getNombre())).thenReturn(false);
        Mockito.when(actividadRepository.save(actividad)).thenReturn(actividad);

        var resultado = actividadService.crearActividad(actividad);

        assertEquals(actividad, resultado);
        verify(actividadRepository).save(actividad);
    }
}
