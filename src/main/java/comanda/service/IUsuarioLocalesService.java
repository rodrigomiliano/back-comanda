package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.UsuarioLocal;

public interface IUsuarioLocalesService {

	List<UsuarioLocal> buscarTodos();
	void guardar(UsuarioLocal usuarioLocal);
	void eliminar(int idUsuarioLocal);
	Optional<UsuarioLocal> buscarUsuarioLocal(int idUsuarioLocal);
}
