package com.t3r3r3x.control_cuotas.categoria.service;

import com.t3r3r3x.control_cuotas.categoria.model.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ServicioCategorias {

    List<Categoria> listarCategorias();

    Categoria obtenerCategoriaPorId(Integer id);

    Categoria crearCategoria(Categoria categoria);
}
