package com.t3r3r3x.control_cuotas.estudiante.controller;

import com.t3r3r3x.control_cuotas.estudiante.dto.EstudianteResponse;
import com.t3r3r3x.control_cuotas.estudiante.dto.GuardarEstudianteRequest;
import com.t3r3r3x.control_cuotas.estudiante.dto.RespuestaPaginada;
import com.t3r3r3x.control_cuotas.estudiante.mapper.EstudianteMapper;
import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import com.t3r3r3x.control_cuotas.estudiante.service.ServicioEstudiantes;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/estudiantes")
@Tag(name = "Estudiantes", description = "Operaciones CRUD de estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final ServicioEstudiantes estudianteService;

    private final EstudianteMapper estudianteMapper;

    @GetMapping
    public ResponseEntity<RespuestaPaginada<EstudianteResponse>> listarEstudiantesPaginados(
            @RequestParam(name = "pagina", defaultValue = "0") @Min(0) int numeroPagina,
            @RequestParam(name = "tamanio", defaultValue = "10") @Positive int tamanioPagina,
            @RequestParam(name = "estado", defaultValue = "true") boolean activo
    ) {
        Page<Estudiante> estudiantes = estudianteService.listarEstudiantesPaginados(
                numeroPagina, tamanioPagina, activo
        );

        return ResponseEntity.ok().body(estudianteMapper.convertirAPaginaRespuesta(estudiantes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> obtenerEstudiantePorId(@PathVariable UUID id) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(id);

        return ResponseEntity.ok(estudianteMapper.convertirARespuesta(estudiante));
    }

    @PostMapping
    public ResponseEntity<EstudianteResponse> crearEstudiante(
            @RequestBody @Valid GuardarEstudianteRequest solicitudEstudiante
    ) {

        Estudiante estudiante = estudianteMapper.convertirAEntidad(solicitudEstudiante);

        Estudiante guardado = estudianteService.crearEstudiante(estudiante);

        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteMapper.convertirARespuesta(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> actualizarEstudiante(
            @PathVariable UUID id,
            @RequestBody @Valid GuardarEstudianteRequest solicitudEstudiante
    ) {

        Estudiante estudiante = estudianteMapper.convertirAEntidad(solicitudEstudiante);

        Estudiante actualizado = estudianteService.actualizarEstudiante(id, estudiante);

        return ResponseEntity.ok(estudianteMapper.convertirARespuesta(actualizado));
    }

    @DeleteMapping("/{id}/desactivar")
    public ResponseEntity<EstudianteResponse> desactivarEstudiante(@PathVariable UUID id) {

        Estudiante desactivado = estudianteService.desactivarEstudiante(id);

        return ResponseEntity.ok(estudianteMapper.convertirARespuesta(desactivado));
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<EstudianteResponse> activarEstudiante(@PathVariable UUID id) {

        Estudiante activado = estudianteService.activarEstudiante(id);

        return ResponseEntity.ok(estudianteMapper.convertirARespuesta(activado));
    }
}
