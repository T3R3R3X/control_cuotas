package com.t3r3r3x.control_cuotas.estudiante.mapper;

import com.t3r3r3x.control_cuotas.estudiante.dto.GuardarEstudianteRequest;
import com.t3r3r3x.control_cuotas.estudiante.dto.EstudianteResponse;
import com.t3r3r3x.control_cuotas.estudiante.dto.RespuestaPaginada;
import com.t3r3r3x.control_cuotas.estudiante.model.Estudiante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    Estudiante convertirAEntidad(GuardarEstudianteRequest solicitud);

    EstudianteResponse convertirARespuesta(Estudiante estudiante);

    @Mapping(source = "content", target = "contenido")
    @Mapping(source = "number", target = "numero")
    @Mapping(source = "size", target = "tamanio")
    @Mapping(source = "totalElements", target = "totalElementos")
    @Mapping(source = "totalPages", target = "totalPaginas")
    RespuestaPaginada<EstudianteResponse> convertirAPaginaRespuesta(Page<Estudiante> paginaEstudiantes);
}
