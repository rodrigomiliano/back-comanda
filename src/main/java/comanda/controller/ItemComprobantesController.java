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
import comanda.entity.ItemComprobante;
import comanda.service.IItemComprobantesService;

@RestController
@RequestMapping("/comanda")
public class ItemComprobantesController {

	@Autowired
	private IItemComprobantesService serviceItemComprobantes;

	@GetMapping("/itemComprobante")
	public List<ItemComprobante> buscarTodos() {
		return serviceItemComprobantes.buscarTodos();
	}

	@GetMapping("/itemComprobante/{id}")
	public Optional<ItemComprobante> buscarItemComprobante(@PathVariable("id") int idItemComprobante) {
		return serviceItemComprobantes.buscarItemComprobante(idItemComprobante);
	}

	@PostMapping("/itemComprobante")
	public ItemComprobante guardar(@RequestBody ItemComprobante itemComprobante) {
		serviceItemComprobantes.guardar(itemComprobante);
		return itemComprobante;
	}

	@PutMapping("/itemComprobante")
	public ItemComprobante modificar(@RequestBody ItemComprobante itemComprobante) {
		serviceItemComprobantes.guardar(itemComprobante);
		return itemComprobante;
	}

	@DeleteMapping("/itemComprobante/{id}")
	public String eliminar(@PathVariable("id") int idItemComprobante) {
		serviceItemComprobantes.eliminar(idItemComprobante);
		return "Registro Eliminado";
	}
}
