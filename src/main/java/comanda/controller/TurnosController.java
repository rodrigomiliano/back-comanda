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
import comanda.entity.Turno;
import comanda.service.ITurnosService;

@RestController
@RequestMapping("/comanda")
public class TurnosController {

	@Autowired
	private ITurnosService serviceTurnos;

	@GetMapping("/turno")
	public List<Turno> buscarTodos() {
		return serviceTurnos.buscarTodos();
	}

	@GetMapping("/turno/{id}")
	public Optional<Turno> buscarTurno(@PathVariable("id") int idTurno) {
		return serviceTurnos.buscarTurno(idTurno);
	}

	@PostMapping("/turno")
	public Turno guardar(@RequestBody Turno turno) {
		serviceTurnos.guardar(turno);
		return turno;
	}

	@PutMapping("/turno")
	public Turno modificar(@RequestBody Turno turno) {
		serviceTurnos.guardar(turno);
		return turno;
	}

	@DeleteMapping("/turno/{id}")
	public String eliminar(@PathVariable("id") int idTurno) {
		serviceTurnos.eliminar(idTurno);
		return "Registro Eliminado";
	}

}
