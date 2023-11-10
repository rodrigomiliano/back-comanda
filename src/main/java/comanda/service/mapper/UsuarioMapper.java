package comanda.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.UsuarioInsertDto;
import comanda.controller.dto.request.UsuarioUpdateDto;
import comanda.controller.dto.response.UsuarioResponse;
import comanda.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "rol.id", source = "rol.id") // Mapea solo el id del rol
    UsuarioResponse mapToUsuarioDto(Usuario usuario);

    //Usuario mapToUsuario(UsuarioResponse usuarioDto);

    List<UsuarioResponse> mapToUsuarioResponseList(List<Usuario> usuarios);

    Usuario mapToUsuario(UsuarioInsertDto usuarioDto);

    Usuario mapToUsuario(UsuarioUpdateDto usuarioDto);

}