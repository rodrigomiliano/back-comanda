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
import comanda.entity.MesaUso;
import comanda.service.IMesaUsosService;

@RestController
@RequestMapping("/comanda")
public class MesaUsosController {

	@Autowired
	private IMesaUsosService serviceMesaUsos;

	@GetMapping("/mesauso")
	public List<MesaUso> buscarTodas() {
		return serviceMesaUsos.buscarTodas();
	}

	@GetMapping("/mesauso/{id}")
	public Optional<MesaUso> buscarMesaUso(@PathVariable("id") int idMesaUso) {
		return serviceMesaUsos.buscarMesaUso(idMesaUso);
	}

	@PostMapping("/mesauso")
	public MesaUso guardar(@RequestBody MesaUso mesauso) {
		serviceMesaUsos.guardar(mesauso);
		return mesauso;
	}

	@PutMapping("/mesauso")
	public MesaUso modificar(@RequestBody MesaUso mesauso) {
		serviceMesaUsos.guardar(mesauso);
		return mesauso;
	}

	@DeleteMapping("/mesauso/{id}")
	public String eliminar(@PathVariable("id") int idMesa) {
		serviceMesaUsos.eliminar(idMesa);
		return "Registro Eliminado";
	}
}
