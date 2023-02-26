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
import comanda.entity.Reserva;
import comanda.entity.Venta;
import comanda.service.jpa.ReservasService;
import comanda.service.jpa.VentasService;

@RestController
@RequestMapping("/comanda")
public class VentasController {

	@Autowired
	private VentasService serviceVentas;

	@GetMapping("/venta")
	public List<Venta> buscarTodas() {
		return serviceVentas.buscarTodas();
	}

	@GetMapping("/venta/{id}")
	public Optional<Venta> buscarVenta(@PathVariable("id") int idVenta) {
		return serviceVentas.buscarVenta(idVenta);
	}

	@PostMapping("/venta")
	public Venta guardar(@RequestBody Venta venta) {
		serviceVentas.guardar(venta);
		return venta;
	}

	@PutMapping("/venta")
	public Venta modificar(@RequestBody Venta venta) {
		serviceVentas.guardar(venta);
		return venta;
	}

	@DeleteMapping("/reserva/{id}")
	public String eliminar(@PathVariable("id") int idReserva) {
		serviceReservas.eliminar(idReserva);
		return "Registro Eliminado";
	}
}
