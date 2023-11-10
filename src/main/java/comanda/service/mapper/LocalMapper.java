package comanda.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.LocalInsertDto;
import comanda.controller.dto.request.LocalUpdateDto;
import comanda.controller.dto.response.LocalResponse;
import comanda.entity.Local;

@Mapper(componentModel = "spring")
public interface LocalMapper {

    LocalMapper INSTANCE = Mappers.getMapper(LocalMapper.class);

    LocalResponse mapToLocalDto(Local local);

    List<LocalResponse> mapToLocalResponseList(List<Local> locales);

    Local mapToLocal(LocalInsertDto localDto);

    Local mapToLocal(LocalUpdateDto localDto);
}
