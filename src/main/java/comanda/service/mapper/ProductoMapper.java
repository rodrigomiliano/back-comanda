package comanda.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.ProductoResponse;
import comanda.entity.Producto;




@Mapper(componentModel = "spring")
public interface ProductoMapper {

	ProductoMapper INSTANCE = Mappers.getMapper( ProductoMapper.class );

	ProductoResponse mapToProductoDTO(Producto producto);

	Producto mapToProducto(ProductoResponse productoDTO);

	List<ProductoResponse> mapToProductoResponseList(List<Producto> productos);

}
