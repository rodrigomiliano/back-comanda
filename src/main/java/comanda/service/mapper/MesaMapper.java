package comanda.service.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.MesaResponse;
import comanda.entity.Mesa;



@Mapper(componentModel = "spring")
public interface MesaMapper {

	MesaMapper INSTANCE = Mappers.getMapper( MesaMapper.class );

	MesaResponse mapToMesaDTO(Mesa mesa);

	List<MesaResponse> mapToListMesaDTO(Collection<Mesa> mesas);

	Mesa mapToMesa(MesaResponse mesaDTO);

	List<Mesa> mapToListMesa(Collection<MesaResponse> mesasDTO);
}
