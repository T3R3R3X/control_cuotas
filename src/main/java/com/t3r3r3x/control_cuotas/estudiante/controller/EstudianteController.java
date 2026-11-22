package com.t3r3r3x.control_cuotas.estudiante.controller;

import com.t3r3r3x.control_cuotas.estudiante.dto.ActualizarEstudianteRequest;
import com.t3r3r3x.control_cuotas.estudiante.dto.CrearEstudianteRequest;
import com.t3r3r3x.control_cuotas.estudiante.dto.EstudianteResponse;
import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import com.t3r3r3x.control_cuotas.estudiante.service.IEstudianteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/estudiantes")
@Tag(name = "Estudiantes", description = "Operaciones CRUD de estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteResponse>> obtenerTodo() {

        List<Estudiante> estudiantes = estudianteService.obtenerTodo();

        return ResponseEntity.ok(estudiantes.stream().map(this::aEstudianteResponse).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<EstudianteResponse> crear(@RequestBody CrearEstudianteRequest crearEstudianteRequest) {

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(crearEstudianteRequest.getNombre());
        estudiante.setApellido(crearEstudianteRequest.getApellido());
        estudiante.setDni(crearEstudianteRequest.getDni());

        Estudiante guardado = estudianteService.crear(estudiante);

        return ResponseEntity.status(HttpStatus.CREATED).body(aEstudianteResponse(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> actualizar(@PathVariable Long id, @RequestBody ActualizarEstudianteRequest actualizarEstudianteRequest) {

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(actualizarEstudianteRequest.getNombre());
        estudiante.setApellido(actualizarEstudianteRequest.getApellido());
        estudiante.setDni(actualizarEstudianteRequest.getDni());

        Estudiante actualizado = estudianteService.actualizar(id, estudiante);

        return ResponseEntity.ok(aEstudianteResponse(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstudianteResponse> eliminar(@PathVariable Long id) {

        Estudiante eliminado = estudianteService.eliminar(id);

        return ResponseEntity.ok(aEstudianteResponse(eliminado));
    }

    private EstudianteResponse aEstudianteResponse(Estudiante estudiante) {
        EstudianteResponse estudianteResponse = new EstudianteResponse();
        estudianteResponse.setId(estudiante.getId());
        estudianteResponse.setNombre(estudiante.getNombre());
        estudianteResponse.setApellido(estudiante.getApellido());
        estudianteResponse.setDni(estudiante.getDni());
        estudianteResponse.setActivo(estudiante.getActivo());
        estudianteResponse.setCreadoEn(estudiante.getCreadoEn());
        estudianteResponse.setActualizadoEn(estudiante.getActualizadoEn());
        return estudianteResponse;
    }
}
