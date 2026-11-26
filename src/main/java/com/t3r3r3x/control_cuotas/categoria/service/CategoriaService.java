package com.t3r3r3x.control_cuotas.categoria.service;

import com.t3r3r3x.control_cuotas.categoria.exception.CategoriaNoEncontradaException;
import com.t3r3r3x.control_cuotas.categoria.exception.NombreCategoriaRegistradaException;
import com.t3r3r3x.control_cuotas.categoria.model.Categoria;
import com.t3r3r3x.control_cuotas.categoria.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService implements ServicioCategorias {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada"));
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {

        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new NombreCategoriaRegistradaException("Nombre de categoría ya registrado");
        }

        return categoriaRepository.save(categoria);
    }
}
