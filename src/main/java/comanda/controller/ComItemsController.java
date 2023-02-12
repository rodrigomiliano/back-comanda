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
import comanda.entity.ComItem;
import comanda.service.IComItemsService;

@RestController
@RequestMapping("/comanda")
public class ComItemsController {

	@Autowired
	private IComItemsService serviceComItems;

	@GetMapping("/comitem")
	public List<ComItem> buscarTodos() {
		return serviceComItems.buscarTodos();
	}

	@GetMapping("/comitem/{id}")
	public Optional<ComItem> buscarComItem(@PathVariable("id") int idComItem) {
		return serviceComItems.buscarComItem(idComItem);
	}

	@PostMapping("/comitem")
	public ComItem guardar(@RequestBody ComItem comItem) {
		serviceComItems.guardar(comItem);
		return comItem;
	}

	@PutMapping("/comitem")
	public ComItem modificar(@RequestBody ComItem comItem) {
		serviceComItems.guardar(comItem);
		return comItem;
	}

	@DeleteMapping("/comitem/{id}")
	public String eliminar(@PathVariable("id") int idComItem) {
		serviceComItems.eliminar(idComItem);
		return "Registro Eliminado";
	}
}
