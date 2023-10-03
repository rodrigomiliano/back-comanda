package comanda.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.ProductoResponse;
import comanda.entity.Producto;




@Mapper(componentModel = "spring", uses = CategoriaMapper.class)
public interface ProductoMapper {

	ProductoMapper INSTANCE = Mappers.getMapper( ProductoMapper.class );

	//@Mapping(target = "categoria.nombre", source = "categoria.nombre", ignore = true)
	ProductoResponse mapToProductoDTO(Producto producto);

	Producto mapToProducto(ProductoResponse productoDTO);

}
