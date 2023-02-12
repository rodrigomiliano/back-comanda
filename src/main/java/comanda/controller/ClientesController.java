package comanda.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import comanda.entity.Cliente;
import comanda.service.IClientesService;

@RestController
@RequestMapping("/comanda")
public class ClientesController {

	@Autowired
	private IClientesService serviceClientes;

	@GetMapping("/cliente")
	public List<Cliente> buscarTodos() {
		return serviceClientes.buscarTodos();
	}

	@GetMapping("/cliente/{id}")
	public Optional<Cliente> buscarCliente(@PathVariable("id") int idCliente) {
		return serviceClientes.buscarCliente(idCliente);
	}

	@PostMapping("/cliente")
	public Cliente guardar(@RequestBody Cliente cliente) {
		serviceClientes.guardar(cliente);
		return cliente;
	}

	@PutMapping("/cliente")
	public Cliente modificar(@RequestBody Cliente cliente) {
		serviceClientes.guardar(cliente);
		return cliente;
	}

	@DeleteMapping("/cliente/{id}")
	public String eliminar(@PathVariable("id") int idCliente) {
		serviceClientes.eliminar(idCliente);
		return "Registro Eliminado";
	}

}
