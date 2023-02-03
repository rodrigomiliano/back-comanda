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
import comanda.entity.Moneda;
import comanda.service.IMonedasService;

@RestController
@RequestMapping("/comanda")
public class MonedasController {

	@Autowired
	private IMonedasService serviceMonedas;

	@GetMapping("/moneda")
	public List<Moneda> buscarTodas() {
		return serviceMonedas.buscarTodas();
	}

	@GetMapping("/moneda/{id}")
	public Optional<Moneda> buscarMoneda(@PathVariable("id") int idMoneda) {
		return serviceMonedas.buscarMoneda(idMoneda);
	}

	@PostMapping("/moneda")
	public Moneda guardar(@RequestBody Moneda moneda) {
		serviceMonedas.guardar(moneda);
		return moneda;
	}

	@PutMapping("/moneda")
	public Moneda modificar(@RequestBody Moneda moneda) {
		serviceMonedas.guardar(moneda);
		return moneda;
	}

	@DeleteMapping("/moneda/{id}")
	public String eliminar(@PathVariable("id") int idMoneda) {
		serviceMonedas.eliminar(idMoneda);
		return "Registro Eliminado";
	}

}
