package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Rol;
import comanda.repository.RolesRepository;
import comanda.service.IRolesService;

@Service
public class RolesService implements IRolesService{

	@Autowired
	private RolesRepository repoRoles;

	public List<Rol> buscarTodos() {
		return repoRoles.findAll();
	}

	public void guardar(Rol rol) {
		repoRoles.save(rol);
	}

	public void eliminar(int idRol) {
		repoRoles.deleteById(idRol);
	}
	
	public Optional<Rol> buscarRol(int idRol) {
		return repoRoles.findById(idRol);
	}
}
