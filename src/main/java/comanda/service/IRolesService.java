package comanda.service;

import java.util.List;
import comanda.entity.Rol;

public interface IRolesService {

	List<Rol> buscarTodos();
	void guardar(Rol rol);
	void eliminar(int idRol) throws Exception;
	Rol buscarRol(int idRol) throws ComandaServiceException;
}
