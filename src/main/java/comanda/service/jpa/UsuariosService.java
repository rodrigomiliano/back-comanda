package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Usuario;
import comanda.repository.UsuariosRepository;
import comanda.service.IUsuariosService;

@Service
public class UsuariosService implements IUsuariosService {

	@Autowired
	private UsuariosRepository repoUsuarios;

	public List<Usuario> buscarTodos() {
		return repoUsuarios.findAll();
	}

	public void guardar(Usuario usuario) {
		repoUsuarios.save(usuario);
	}

	public void eliminar(int idUsuario) {
		repoUsuarios.deleteById(idUsuario);
	}
	
	public Optional<Usuario> buscarUsuario(int idUsuario) {
		return repoUsuarios.findById(idUsuario);
	}
}
