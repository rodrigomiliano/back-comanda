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
import comanda.entity.ItemComanda;
import comanda.service.IItemComandasService;

@RestController
@RequestMapping("/comanda")
public class ItemComandasController {

	@Autowired
	private IItemComandasService serviceItemComandas;

	@GetMapping("/itemcomanda")
	public List<ItemComanda> buscarTodos() {
		return serviceItemComandas.buscarTodos();
	}

	@GetMapping("/itemcomanda/{id}")
	public Optional<ItemComanda> buscarItemComanda(@PathVariable("id") int idItemComanda) {
		return serviceItemComandas.buscarItemComanda(idItemComanda);
	}

	@PostMapping("/itemcomanda")
	public ItemComanda guardar(@RequestBody ItemComanda itemComanda) {
		serviceItemComandas.guardar(itemComanda);
		return itemComanda;
	}

	@PutMapping("/itemcomanda")
	public ItemComanda modificar(@RequestBody ItemComanda itemComanda) {
		serviceItemComandas.guardar(itemComanda);
		return itemComanda;
	}

	@DeleteMapping("/itemcomanda/{id}")
	public String eliminar(@PathVariable("id") int idItemComanda) {
		serviceItemComandas.eliminar(idItemComanda);
		return "Registro Eliminado";
	}
}