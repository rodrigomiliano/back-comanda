package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Cliente;
import comanda.entity.Rol;
import comanda.repository.RolesRepository;
import comanda.service.IRolesService;

@Service
public class RolesService implements IRolesService{

	@Autowired
	private RolesRepository repoRoles;
	
	public List<Rol> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Rol> roles = repoRoles.findAll(); // consola de spring
		System.out.println("Listado de Roles: ");
		roles.forEach(t -> {
			System.out.println(t);
		});
		return repoRoles.findAll(); // postman
	}
	
	public void guardar(Rol rol) {
		System.out.println("------------------------------------------------------------");
		repoRoles.save(rol);
		System.out.println("Guardando " + rol);
	}

	public void eliminar(int idRol) {
		System.out.println("Eliminando registro: " + buscarRol(idRol));
		repoRoles.deleteById(idRol);
	}
	
	public Optional<Rol> buscarRol(int idRol) {
		System.out.println("------------------------------------------------------------");
		Optional<Rol> optional = repoRoles.findById(idRol);
		if (optional.isPresent()) {
			Rol u = optional.get();
			System.out.println("Elegiste " + u);
			return repoRoles.findById(idRol);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Rol n° " + idRol);
		}
		return null;
	}
}
