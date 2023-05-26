package comanda.service.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.ComandaDTO;
import comanda.entity.Comanda;



@Mapper(componentModel = "spring")
public interface ComandaMapper {

	ComandaMapper INSTANCE = Mappers.getMapper( ComandaMapper.class );

	@Mapping (target = "mesaUsoId", source = "comanda.mesaUso.id")
	ComandaDTO mapToComandaDTO(Comanda comanda);

	List<ComandaDTO> mapToListComandaDTO(Collection<Comanda> comandas);

	Comanda mapToComanda(ComandaDTO comandaDTO);

	List<Comanda> mapToListComanda(Collection<ComandaDTO> comandasDTO);
}
