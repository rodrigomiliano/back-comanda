package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Cliente;

public interface IClientesService {

	List<Cliente> buscarTodos();
	void guardar(Cliente cliente);
	void eliminar(int idCliente);
	Optional<Cliente> buscarCliente(int idCliente);
}
