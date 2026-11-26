package com.t3r3r3x.control_cuotas.categoria.mapper;

import com.t3r3r3x.control_cuotas.categoria.dto.CategoriaResponse;
import com.t3r3r3x.control_cuotas.categoria.dto.GuardarCategoriaRequest;
import com.t3r3r3x.control_cuotas.categoria.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria convertirAEntidad(GuardarCategoriaRequest solicitud);

    List<CategoriaResponse> convertirARespuesta(List<Categoria> categorias);

    CategoriaResponse convertirARespuesta(Categoria categoria);
}
