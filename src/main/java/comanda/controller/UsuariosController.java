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
import comanda.entity.Usuario;
import comanda.service.IUsuariosService;

@RestController
@RequestMapping("/comanda")
public class UsuariosController {

	@Autowired
	private IUsuariosService serviceUsuarios;

	@GetMapping("/usuario")
	public List<Usuario> buscarTodos() {
		return serviceUsuarios.buscarTodos();
	}

	@GetMapping("/usuario/{id}")
	public Optional<Usuario> buscarUsuario(@PathVariable("id") int idUsuario) {
		return serviceUsuarios.buscarUsuario(idUsuario);
	}

	@PostMapping("/usuario")
	public Usuario guardar(@RequestBody Usuario usuario) {
		serviceUsuarios.guardar(usuario);
		return usuario;
	}

	@PutMapping("/usuario")
	public Usuario modificar(@RequestBody Usuario usuario) {
		serviceUsuarios.guardar(usuario);
		return usuario;
	}

	@DeleteMapping("/usuario/{id}")
	public String eliminar(@PathVariable("id") int idUsuario) {
		serviceUsuarios.eliminar(idUsuario);
		return "Registro Eliminado";
	}

}
