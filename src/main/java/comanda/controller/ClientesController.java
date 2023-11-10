package comanda.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comanda.controller.dto.request.ClienteInsertDto;
import comanda.controller.dto.request.ClienteUpdateDto;
import comanda.controller.dto.response.ClienteResponse;
import comanda.entity.Cliente;
import comanda.service.ComandaServiceException;
import comanda.service.IClientesService;
import comanda.service.mapper.ClienteMapper;

@RestController
@RequestMapping("/comanda")
public class ClientesController {

    private final Logger LOGGER = LoggerFactory.getLogger(ClientesController.class);

    @Autowired
    private IClientesService serviceClientes;

    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    @GetMapping("/cliente")
    public List<ClienteResponse> buscarTodos() {
        List<Cliente> clientes = serviceClientes.buscarTodos();
        List<ClienteResponse> response = clienteMapper.mapToClienteResponseList(clientes);
        return response;
    }

    @GetMapping("/cliente/{id}")
    public ClienteResponse buscarCliente(@PathVariable("id") int idCliente) {
        Cliente cliente = null;
        try {
            cliente = serviceClientes.buscarCliente(idCliente);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LOGGER.info(">>>>>> Cliente: " + cliente);
        
        ClienteResponse clienteResponse = clienteMapper.mapToClienteDto(cliente);
        LOGGER.info(">>>>>> clienteResponse: " + clienteResponse);
        return clienteResponse;
    }

    @PostMapping("/cliente")
    public ClienteResponse guardar(@RequestBody ClienteInsertDto clienteDto) throws ComandaServiceException {

        // Creamos el Cliente a insertar
        Cliente cliente = null;
        cliente = clienteMapper.mapToCliente(clienteDto);
        LOGGER.info(">>>>>> Cliente luego del mapper : " + cliente);

        cliente = serviceClientes.guardar(cliente);

        ClienteResponse clienteResponse = clienteMapper.mapToClienteDto(cliente);
        LOGGER.info(">>>>>> clienteResponse: " + clienteResponse);

        return clienteResponse;
    }

    @PutMapping("/cliente/{id}")
    public ClienteResponse modificar(@PathVariable("id") int idCliente, @RequestBody ClienteUpdateDto clienteDto)
            throws ComandaServiceException {

        Cliente cliente = null;
        cliente = clienteMapper.mapToCliente(clienteDto);
        cliente.setId(idCliente);
        LOGGER.info(">>>>>> Cliente luego del mapper : " + cliente);

        LOGGER.info("idCliente: " + idCliente);
        LOGGER.info("Cliente: " + clienteDto.toString());

        cliente = serviceClientes.modificar(cliente);
        LOGGER.info("Cliente guardado: " + cliente.toString());

        ClienteResponse clienteResponse = clienteMapper.mapToClienteDto(cliente);
        LOGGER.info(">>>>>> clienteResponse: " + clienteResponse);

        return clienteResponse;
    }

    @DeleteMapping("/cliente/{id}")
    public String eliminar(@PathVariable("id") int idCliente) {
        try {
            serviceClientes.eliminar(idCliente);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Registro Eliminado";
    }
}
