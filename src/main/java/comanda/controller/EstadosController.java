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
import comanda.entity.Estado;
import comanda.service.IEstadosService;

@RestController
@RequestMapping("/comanda")
public class EstadosController {

	@Autowired
	private IEstadosService serviceEstados;

	@GetMapping("/estado")
	public List<Estado> buscarTodos() {
		return serviceEstados.buscarTodos();
	}

	@GetMapping("/estado/{id}")
	public Optional<Estado> buscarEstado(@PathVariable("id") int idEstado) {
		return serviceEstados.buscarEstado(idEstado);
	}

	@PostMapping("/estado")
	public Estado guardar(@RequestBody Estado estado) {
		serviceEstados.guardar(estado);
		return estado;
	}

	@PutMapping("/estado")
	public Estado modificar(@RequestBody Estado estado) {
		serviceEstados.guardar(estado);
		return estado;
	}

	@DeleteMapping("/estado/{id}")
	public String eliminar(@PathVariable("id") int idEstado) {
		serviceEstados.eliminar(idEstado);
		return "Registro Eliminado";
	}

}
