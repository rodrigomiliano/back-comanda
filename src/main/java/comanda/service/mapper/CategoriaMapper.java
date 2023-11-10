package comanda.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.CategoriaInsertDto;
import comanda.controller.dto.request.CategoriaUpdateDto;
import comanda.controller.dto.response.CategoriaResponse;
import comanda.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

	CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

	CategoriaResponse mapToCategoriaDto(Categoria categoria);

	List<CategoriaResponse> mapToCategoriaResponseList(List<Categoria> categorias);
	
	Categoria mapToCategoria(CategoriaInsertDto categoriaDto);
	
	Categoria mapToCategoria(CategoriaUpdateDto categoriaDto);	
	
}
