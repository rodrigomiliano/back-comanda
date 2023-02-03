package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Usuario;

public interface IUsuariosService {

	List<Usuario> buscarTodos();
	void guardar(Usuario usuario);
	void eliminar(int idUsuario);
	Optional<Usuario> buscarUsuario(int idUsuario);
}
