package comanda.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.ProductoResponse;
import comanda.entity.Producto;




@Mapper(componentModel = "spring", uses = CategoriaMapper.class)
public interface ProductoMapper {

	ProductoMapper INSTANCE = Mappers.getMapper( ProductoMapper.class );

	ProductoResponse mapToProductoDTO(Producto producto);

	Producto matToProducto(ProductoResponse productoDTO);

}
