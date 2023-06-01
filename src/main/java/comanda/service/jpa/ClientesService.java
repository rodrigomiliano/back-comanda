package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Cliente;
import comanda.repository.ClientesRepository;
import comanda.service.IClientesService;

@Service
public class ClientesService implements IClientesService {

	@Autowired
	private ClientesRepository repoClientes;

	public List<Cliente> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Cliente> clientes = repoClientes.findAll(); // consola de spring
		System.out.println("Listado de Clientes: ");
		clientes.forEach(t -> {
			System.out.println(t);
		});
		return repoClientes.findAll(); // postman
	}
		
	public void guardar(Cliente cliente) {
		System.out.println("------------------------------------------------------------");
		repoClientes.save(cliente);
		System.out.println("Guardando " + cliente);
	}
	
	public void eliminar(int idCliente) {
		System.out.println("Eliminando registro: " + buscarCliente(idCliente));
		repoClientes.deleteById(idCliente);
	}
		
	public Optional<Cliente> buscarCliente(int idCliente) {
		System.out.println("------------------------------------------------------------");
		Optional<Cliente> optional = repoClientes.findById(idCliente);
		if (optional.isPresent()) {
			Cliente u = optional.get();
			System.out.println("Elegiste " + u);
			return repoClientes.findById(idCliente);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Cliente n° " + idCliente);
		}
		return null;
	}	
	
}
