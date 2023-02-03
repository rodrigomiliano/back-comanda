package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Rol;

public interface IRolesService {

	List<Rol> buscarTodos();
	void guardar(Rol rol);
	void eliminar(int idRol);
	Optional<Rol> buscarRol(int idRol);
}
