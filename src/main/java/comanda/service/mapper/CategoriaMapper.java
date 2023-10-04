package comanda.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import comanda.controller.dto.response.CategoriaResponse;
import comanda.entity.Categoria;


@Mapper(componentModel = "spring")
public interface CategoriaMapper {

	CategoriaMapper INSTANCE = Mappers.getMapper( CategoriaMapper.class );

	CategoriaResponse mapToCategoriaResponse(Categoria categoria);

	Categoria mapToCategorio(CategoriaResponse categoria);


}
