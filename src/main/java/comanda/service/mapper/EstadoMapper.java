package comanda.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.EstadoInsertDto; 
import comanda.controller.dto.request.EstadoUpdateDto; 
import comanda.controller.dto.response.EstadoResponse; 
import comanda.entity.Estado; 

@Mapper(componentModel = "spring")
public interface EstadoMapper {

    EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

    EstadoResponse mapToEstadoDto(Estado estado); 

    List<EstadoResponse> mapToEstadoResponseList(List<Estado> estados);

    Estado mapToEstado(EstadoInsertDto estadoDto); 

    Estado mapToEstado(EstadoUpdateDto estadoDto); 
}
