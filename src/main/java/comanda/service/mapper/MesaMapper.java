package comanda.service.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.MesaDTO;
import comanda.entity.Mesa;



@Mapper(componentModel = "spring")
public interface MesaMapper {

	MesaMapper INSTANCE = Mappers.getMapper( MesaMapper.class );

	MesaDTO mapToMesaDTO(Mesa mesa);

	List<MesaDTO> mapToListMesaDTO(Collection<Mesa> mesas);

	Mesa mapToMesa(MesaDTO mesaDTO);

	List<Mesa> mapToListMesa(Collection<MesaDTO> mesasDTO);
}
