package comanda.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import comanda.controller.dto.response.TurnoResponse;
import comanda.entity.Turno;

@Mapper(componentModel = "spring")
public interface TurnoMapper {

	TurnoMapper INSTANCE = Mappers.getMapper(TurnoMapper.class);

	@Mapping(target = "estado.id", source = "estado.id") // Mapea solo el id del estado
	TurnoResponse mapToTurnoDto(Turno turno);

	Turno mapToTurno(TurnoResponse turnoDto);

	List<TurnoResponse> mapToTurnoResponseList(List<Turno> turnos);

}
