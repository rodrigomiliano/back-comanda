package comanda.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import comanda.controller.dto.response.RolResponse;
import comanda.entity.Rol;

@Mapper(componentModel = "spring")
public interface RolMapper {

	RolMapper INSTANCE = Mappers.getMapper(RolMapper.class);

	RolResponse mapToRolDto(Rol rol);

	Rol mapToRol(RolResponse rolDto);
	
	List<RolResponse> mapToRolResponseList(List<Rol> roles);

}
