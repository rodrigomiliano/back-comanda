package comanda.service;

import java.util.List;
import comanda.entity.Rol;

public interface IRolesService {

	List<Rol> buscarTodos();
	Rol guardar(Rol rol) throws ComandaServiceException;
	Rol modificar(Rol rol) throws ComandaServiceException;
	void eliminar(int idRol) throws Exception;
	Rol buscarRol(int idRol) throws ComandaServiceException;
}
