package comanda.service.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.response.ComandaResponse;
import comanda.controller.dto.response.MesaUsoResponse;
import comanda.entity.MesaUso;


@Mapper(componentModel = "spring")
public interface MesaUsoMapper{

	MesaUsoMapper INSTANCE = Mappers.getMapper( MesaUsoMapper.class );


	@Mapping(target = "mesa", source = "mesa")
	MesaUsoResponse mapToMesaUsoDTO(MesaUso mesaUso);

	List<MesaUsoResponse> mapToListMesaUsoDTO(Collection<MesaUso> mesasUso);


	MesaUso mapToMesaUso(MesaUsoResponse mesaUsoDTO);

	List<MesaUso> mapToListMesaUso(Collection<MesaUsoResponse> MesaUsosDTO);

}
