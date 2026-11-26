package com.t3r3r3x.control_cuotas.categoria.controller;

import com.t3r3r3x.control_cuotas.categoria.dto.CategoriaResponse;
import com.t3r3r3x.control_cuotas.categoria.dto.GuardarCategoriaRequest;
import com.t3r3r3x.control_cuotas.categoria.mapper.CategoriaMapper;
import com.t3r3r3x.control_cuotas.categoria.model.Categoria;
import com.t3r3r3x.control_cuotas.categoria.service.ServicioCategorias;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Operaciones CRUD de categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final ServicioCategorias categoriaService;

    private final CategoriaMapper categoriaMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {

        List<Categoria> categorias = categoriaService.listarCategorias();

        return ResponseEntity.ok().body(categoriaMapper.convertirARespuesta(categorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerCategoriaPorId(@PathVariable @Positive Integer id) {

        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);

        return ResponseEntity.ok(categoriaMapper.convertirARespuesta(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crearCategoria(@RequestBody @Valid GuardarCategoriaRequest solicitudCategoria) {

        Categoria categoria = categoriaMapper.convertirAEntidad(solicitudCategoria);

        Categoria guardado = categoriaService.crearCategoria(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaMapper.convertirARespuesta(guardado));
    }
}
