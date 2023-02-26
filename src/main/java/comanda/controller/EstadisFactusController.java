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

import comanda.entity.EstadisFactu;
import comanda.service.IEstadisFactusService;

@RestController
@RequestMapping("/comanda")
public class EstadisFactusController {

	@Autowired
	private IEstadisFactusService serviceEstadisFactus;

	@GetMapping("/estadisfactu")
	public List<EstadisFactu> buscarTodas() {
		return serviceEstadisFactus.buscarTodas();		
	}

	@GetMapping("/estadisfactu/{id}")
	public Optional<EstadisFactu> buscarEstadisFactu(@PathVariable("id") int idEstadisFactu) {
		return serviceEstadisFactus.buscarEstadisFactu(idEstadisFactu);
	}

	@PostMapping("/estadisfactu")
	public EstadisFactu guardar(@RequestBody EstadisFactu estadisfactu) {
		serviceEstadisFactus.guardar(estadisfactu);
		return estadisfactu;
	}

	@PutMapping("/estadisfactu")
	public EstadisFactu modificar(@RequestBody EstadisFactu estadisfactu) {
		serviceEstadisFactus.guardar(estadisfactu);
		return estadisfactu;
	}

	@DeleteMapping("/estadisfactu/{id}")
	public String eliminar(@PathVariable("id") int idEstadisFactu) {
		serviceEstadisFactus.eliminar(idEstadisFactu);
		return "Registro Eliminado";
	}

}
