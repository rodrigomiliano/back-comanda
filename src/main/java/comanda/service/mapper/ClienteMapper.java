package comanda.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import comanda.controller.dto.request.ClienteInsertDto;
import comanda.controller.dto.request.ClienteUpdateDto;
import comanda.controller.dto.response.ClienteResponse;
import comanda.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteResponse mapToClienteDto(Cliente cliente);

    //Cliente mapToCliente(ClienteResponse clienteDto);

    List<ClienteResponse> mapToClienteResponseList(List<Cliente> clientes);

    Cliente mapToCliente(ClienteInsertDto clienteDto);

    Cliente mapToCliente(ClienteUpdateDto clienteDto);
}
