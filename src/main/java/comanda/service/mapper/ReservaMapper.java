package comanda.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.ReservaInsertDto;
import comanda.controller.dto.request.ReservaUpdateDto;
import comanda.controller.dto.response.ReservaResponse;
import comanda.entity.Reserva;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    @Mapping(target = "cliente.id", source = "cliente.id")
    @Mapping(target = "mesa.id", source = "mesa.id")  
    @Mapping(target = "estado.id", source = "estado.id")
    @Mapping(target = "turno.id", source = "turno.id")  
    ReservaResponse mapToReservaDto(Reserva reserva);
    
    List<ReservaResponse> mapToReservaResponseList(List<Reserva> reservas);

    Reserva mapToReserva(ReservaInsertDto reservaDto);

    Reserva mapToReserva(ReservaUpdateDto reservaDto);
}
