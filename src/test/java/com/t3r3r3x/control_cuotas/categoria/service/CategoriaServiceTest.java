package com.t3r3r3x.control_cuotas.categoria.service;

import com.t3r3r3x.control_cuotas.categoria.exception.CategoriaNoEncontradaException;
import com.t3r3r3x.control_cuotas.categoria.exception.NombreCategoriaRegistradaException;
import com.t3r3r3x.control_cuotas.categoria.model.Categoria;
import com.t3r3r3x.control_cuotas.categoria.repository.CategoriaRepository;
import com.t3r3r3x.control_cuotas.estudiante.exception.EstudianteNoEncontradoException;
import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Test Categoria");
    }

    @Test
    void deberiaListarCategorias() {

        Mockito.when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));

        var resultado = categoriaService.listarCategorias();

        assertEquals(1, resultado.size());
        assertEquals(1, resultado.getFirst().getId());
        assertEquals("Test Categoria", resultado.getFirst().getNombre());
    }

    @Test
    void deberiaObtenerCategoriaPorId() {

        var categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Test Categoria");

        Mockito.when(categoriaRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(categoria));

        var resultado = categoriaService.obtenerCategoriaPorId(categoria.getId());

        verify(categoriaRepository).findById(Mockito.any(Integer.class));

        assertEquals(categoria, resultado);
    }

    @Test
    void deberiaLanzarExcepcionSiNoEncuentraLaCategoria() {

        var excepcion = assertThrows(
                CategoriaNoEncontradaException.class,
                () -> categoriaService.obtenerCategoriaPorId(999)
        );

        verify(categoriaRepository).findById(Mockito.any(Integer.class));

        assertEquals("Categoría no encontrada", excepcion.getMessage());
    }

    @Test
    void deberiaLanzarExcepcionSiElNombreYaEstaRegistrado() {

        var categoria = new Categoria();
        categoria.setNombre("Test Categoria");

        Mockito.when(categoriaRepository.existsByNombre(Mockito.anyString())).thenReturn(true);

        var excepcion = assertThrows(NombreCategoriaRegistradaException.class, () -> categoriaService.crearCategoria(categoria));

        assertEquals("Nombre de categoría ya registrado", excepcion.getMessage());
    }

    @Test
    void deberiaCrearUnaNuevaCategoria() {

        var nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre("Nueva Categoria");

        Mockito.when(categoriaRepository.existsByNombre(Mockito.anyString())).thenReturn(false);
        Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(nuevaCategoria);

        var resultado = categoriaService.crearCategoria(nuevaCategoria);

        assertEquals(nuevaCategoria, resultado);
    }
}