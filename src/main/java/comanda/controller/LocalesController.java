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
import comanda.entity.Local;
import comanda.service.ILocalesService;

@RestController
@RequestMapping("/comanda")
public class LocalesController {

	@Autowired
	private ILocalesService serviceLocales;

	@GetMapping("/local")
	public List<Local> buscarTodos() {
		return serviceLocales.buscarTodos();
	}

	@GetMapping("/local/{id}")
	public Optional<Local> buscarLocal(@PathVariable("id") int idLocal) {
		return serviceLocales.buscarLocal(idLocal);
	}

	@PostMapping("/local")
	public Local guardar(@RequestBody Local local) {
		serviceLocales.guardar(local);
		return local;
	}

	@PutMapping("/local")
	public Local modificar(@RequestBody Local local) {
		serviceLocales.guardar(local);
		return local;
	}

	@DeleteMapping("/local/{id}")
	public String eliminar(@PathVariable("id") int idLocal) {
		serviceLocales.eliminar(idLocal);
		return "Registro Eliminado";
	}

}
