package comanda.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.MesaInsertDto;
import comanda.controller.dto.request.MesaUpdateDto;
import comanda.controller.dto.response.MesaResponse;
import comanda.entity.Mesa;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaMapper INSTANCE = Mappers.getMapper(MesaMapper.class);

    @Mapping(target = "estado.id", source = "estado.id") // Mapea solo el id del estado
    MesaResponse mapToMesaDto(Mesa mesa);

    List<MesaResponse> mapToMesaResponseList(List<Mesa> mesas);

    Mesa mapToMesa(MesaInsertDto mesaDto);

    Mesa mapToMesa(MesaUpdateDto mesaDto);
}
