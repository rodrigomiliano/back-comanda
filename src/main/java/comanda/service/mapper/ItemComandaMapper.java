package comanda.service.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

import comanda.controller.dto.request.ItemComandaInsertRequest;
import comanda.controller.dto.response.ItemComandaResponse;
import comanda.entity.ItemComanda;

@Mapper(componentModel = "spring")
public interface ItemComandaMapper {


	//ItemComandaMapper INSTANCE = Mappers.getMapper( ItemComandaMapper.class );

	ItemComandaResponse mapToItemComandaDTO(ItemComanda itemComanda);

	List<ItemComandaResponse> mapToListItemComandaDTO(Collection<ItemComanda> itemComandas);


	@Mappings({
		@Mapping(target= "producto.id", source = "productoId"),
		@Mapping(target= "cantidad", source = "cantidad")}
	)
	ItemComanda mapToItemComanda(ItemComandaInsertRequest itemComandaInsertRequest);

	List<ItemComanda> mapToListItemComanda(Collection<ItemComandaInsertRequest> itemComandaInsertRequest);

}
