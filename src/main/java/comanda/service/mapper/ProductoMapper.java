package comanda.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.ProductoInsertDto;
import comanda.controller.dto.request.ProductoUpdateDto;
import comanda.controller.dto.response.ProductoResponse;
import comanda.entity.Producto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

	ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

	@Mapping(target = "categoria.id", source = "categoria.id") // Mapea solo el id de la categoría
	ProductoResponse mapToProductoDto(Producto producto);

	// Producto mapToProducto(ProductoResponse productoDto);

	List<ProductoResponse> mapToProductoResponseList(List<Producto> productos);

	Producto mapToProducto(ProductoInsertDto productoDto);

	Producto mapToProducto(ProductoUpdateDto productoDto);

}
