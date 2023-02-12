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
		return repoClientes.findAll();
	}

	public void guardar(Cliente Cliente) {
		repoClientes.save(Cliente);
	}

	public void eliminar(int idCliente) {
		repoClientes.deleteById(idCliente);
	}
	
	public Optional<Cliente> buscarCliente(int idCliente) {
		return repoClientes.findById(idCliente);
	}
}
