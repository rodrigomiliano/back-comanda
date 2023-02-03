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
import comanda.service.jpa.ReservasService;

@RestController
@RequestMapping("/comanda")
public class ReservasController {

	@Autowired
	private ReservasService serviceReservas;

	@GetMapping("/reserva")
	public List<Reserva> buscarTodas() {
		return serviceReservas.buscarTodas();
	}

	@GetMapping("/reserva/{id}")
	public Optional<Reserva> buscarReserva(@PathVariable("id") int idReserva) {
		return serviceReservas.buscarReserva(idReserva);
	}

	@PostMapping("/reserva")
	public Reserva guardar(@RequestBody Reserva reserva) {
		serviceReservas.guardar(reserva);
		return reserva;
	}

	@PutMapping("/reserva")
	public Reserva modificar(@RequestBody Reserva reserva) {
		serviceReservas.guardar(reserva);
		return reserva;
	}

	@DeleteMapping("/reserva/{id}")
	public String eliminar(@PathVariable("id") int idReserva) {
		serviceReservas.eliminar(idReserva);
		return "Registro Eliminado";
	}
}
