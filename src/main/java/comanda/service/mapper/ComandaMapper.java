package comanda.service.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.ComandaResponse;
import comanda.entity.Comanda;



@Mapper(componentModel = "spring")
public interface ComandaMapper {

	ComandaMapper INSTANCE = Mappers.getMapper( ComandaMapper.class );

	@Mapping (target = "mesaUsoId", source = "comanda.mesaUso.id")
	ComandaResponse mapToComandaDTO(Comanda comanda);

	List<ComandaResponse> mapToListComandaDTO(Collection<Comanda> comandas);

	Comanda mapToComanda(ComandaResponse comandaDTO);

	List<Comanda> mapToListComanda(Collection<ComandaResponse> comandasDTO);
}
