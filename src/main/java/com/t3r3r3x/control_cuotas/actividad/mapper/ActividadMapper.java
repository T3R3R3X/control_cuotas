package com.t3r3r3x.control_cuotas.actividad.mapper;

import com.t3r3r3x.control_cuotas.actividad.dto.ActividadResponse;
import com.t3r3r3x.control_cuotas.actividad.dto.GuardarActividadRequest;
import com.t3r3r3x.control_cuotas.actividad.model.Actividad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActividadMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    Actividad convertirAEntidad(GuardarActividadRequest solicitud);

    List<ActividadResponse> convertirARespuesta(List<Actividad> actividades);

    ActividadResponse convertirARespuesta(Actividad actividad);
}
