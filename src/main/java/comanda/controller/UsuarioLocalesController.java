package comanda.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comanda.entity.UsuarioLocal;
import comanda.service.IUsuarioLocalesService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class UsuarioLocalesController {

	@Autowired
	private IUsuarioLocalesService serviceUsuarioLocales;

	@GetMapping("/usuariolocal")
	public List<UsuarioLocal> buscarTodos() {
		return serviceUsuarioLocales.buscarTodos();
	}

	@GetMapping("/usuariolocal/{id}")
	public Optional<UsuarioLocal> buscarUsuarioLocal(@PathVariable("id") int idUsuarioLocal) {
		return serviceUsuarioLocales.buscarUsuarioLocal(idUsuarioLocal);
	}

	@PostMapping("/usuariolocal")
	public UsuarioLocal guardar(@RequestBody UsuarioLocal usuarioLocal) {
		serviceUsuarioLocales.guardar(usuarioLocal);
		return usuarioLocal;
	}

	@PutMapping("/usuariolocal")
	public UsuarioLocal modificar(@RequestBody UsuarioLocal usuarioLocal) {
		serviceUsuarioLocales.guardar(usuarioLocal);
		return usuarioLocal;
	}

	@DeleteMapping("/usuariolocal/{id}")
	public String eliminar(@PathVariable("id") int idUsuarioLocal) {
		serviceUsuarioLocales.eliminar(idUsuarioLocal);
		return "Registro Eliminado";
	}

}
