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
import comanda.entity.Rol;
import comanda.service.IRolesService;

@RestController
@RequestMapping("/comanda")
public class RolesController {

	@Autowired
	private IRolesService serviceRoles;

	@GetMapping("/rol")
	public List<Rol> buscarTodos() {
		return serviceRoles.buscarTodos();
	}

	@GetMapping("/rol/{id}")
	public Optional<Rol> buscarRol(@PathVariable("id") int idRol) {
		return serviceRoles.buscarRol(idRol);
	}

	@PostMapping("/rol")
	public Rol guardar(@RequestBody Rol rol) {
		serviceRoles.guardar(rol);
		return rol;
	}

	@PutMapping("/rol")
	public Rol modificar(@RequestBody Rol rol) {
		serviceRoles.guardar(rol);
		return rol;
	}

	@DeleteMapping("/rol/{id}")
	public String eliminar(@PathVariable("id") int idRol) {
		serviceRoles.eliminar(idRol);
		return "Registro Eliminado";
	}

}
