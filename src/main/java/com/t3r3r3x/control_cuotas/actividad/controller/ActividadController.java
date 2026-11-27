package com.t3r3r3x.control_cuotas.actividad.controller;

import com.t3r3r3x.control_cuotas.actividad.dto.ActividadResponse;
import com.t3r3r3x.control_cuotas.actividad.dto.GuardarActividadRequest;
import com.t3r3r3x.control_cuotas.actividad.mapper.ActividadMapper;
import com.t3r3r3x.control_cuotas.actividad.model.Actividad;
import com.t3r3r3x.control_cuotas.actividad.service.ServicioActividades;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actividades")
@Tag(name = "Actividades", description = "Operaciones de consulta y creación de actividades")
@RequiredArgsConstructor
public class ActividadController {

    private final ServicioActividades actividadService;
    private final ActividadMapper actividadMapper;

    @GetMapping
    public ResponseEntity<List<ActividadResponse>> obtenerTodasLasActividades() {
        List<Actividad> actividades = actividadService.obtenerTodasLasActividades();

        return ResponseEntity.ok(actividadMapper.convertirARespuesta(actividades));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponse> obtenerUnaActividadPorId(@PathVariable @Positive Integer id) {
        Actividad actividad = actividadService.obtenerUnaActividadPorId(id);

        return ResponseEntity.ok(actividadMapper.convertirARespuesta(actividad));
    }

    @PostMapping
    public ResponseEntity<ActividadResponse> crearActividad(
            @RequestBody @Valid GuardarActividadRequest solicitudActividad
    ) {
        Actividad actividad = actividadMapper.convertirAEntidad(solicitudActividad);
        Actividad guardada = actividadService.crearActividad(actividad);

        return ResponseEntity.status(HttpStatus.CREATED).body(actividadMapper.convertirARespuesta(guardada));
    }
}
