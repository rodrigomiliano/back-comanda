package comanda.service.jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Cliente;
import comanda.repository.ClientesRepository;
import comanda.service.ComandaServiceException;
import comanda.service.IClientesService;

@Service
public class ClientesService implements IClientesService {

    private final Logger LOGGER = LoggerFactory.getLogger(ClientesService.class);

    @Autowired
    private ClientesRepository repoClientes;

    public List<Cliente> buscarTodos() {
        System.out.println("------------------------------------------------------------");
        List<Cliente> clientes = repoClientes.findAll();
        System.out.println("Listado de Clientes: ");
        clientes.forEach(c -> {
            System.out.println(c);
        });
        return repoClientes.findAll();
    }

    public Cliente guardar(Cliente cliente) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        LOGGER.info(">>>>>> Cliente a guardar: " + cliente);        
        LOGGER.info(">>>>>> Cliente a guardar via el repo: " + cliente);
        System.out.println("Guardando " + cliente);
        return repoClientes.save(cliente);
    }

    public Cliente modificar(Cliente cliente) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        LOGGER.info(">>>>>> Cliente a guardar: " + cliente);

        // Buscar el cliente existente
        Cliente clienteNew = null;
        try {
            clienteNew = buscarCliente(cliente.getId());
        } catch (ComandaServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
        // por el Body
        clienteNew.setUsuario(cliente.getUsuario());
        clienteNew.setNombre(cliente.getNombre());
        clienteNew.setApellido(cliente.getApellido());
        clienteNew.setDni(cliente.getDni());
        clienteNew.setEmail(cliente.getEmail());
        clienteNew.setTelefono(cliente.getTelefono());
        clienteNew.setContrasena(cliente.getContrasena());        
     
        LOGGER.info("usr: " + cliente.toString());
        LOGGER.info(">>>>>> Cliente a guardar via el repo: " + cliente);
        System.out.println("Guardando " + cliente);

        return guardar(cliente);
    }

    public void eliminar(int idCliente) throws Exception {
        System.out.println("Eliminando registro: " + buscarCliente(idCliente));
        repoClientes.deleteById(idCliente);
    }

    public Cliente buscarCliente(int idCliente) throws ComandaServiceException {
        System.out.println("------------------------------------------------------------");
        Optional<Cliente> optional = repoClientes.findById(idCliente);
        if (optional.isPresent()) {
            Cliente c = optional.get();
            System.out.println("Elegiste " + c);
            return c;
        } else {
            System.out.println("------------------------------------------------------------");
            System.out.println("No existe el Cliente n° " + idCliente);
            throw new ComandaServiceException("US001", "No existe el Cliente n° " + idCliente);
        }
    }
}
