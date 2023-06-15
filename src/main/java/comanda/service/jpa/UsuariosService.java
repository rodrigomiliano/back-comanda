package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Rol;
import comanda.entity.Usuario;
import comanda.repository.UsuariosRepository;
import comanda.service.IUsuariosService;

@Service
public class UsuariosService implements IUsuariosService {

	// Empezar a actualizar como el ejemplo de categorias
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	public List<Usuario> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Usuario> usuarios = repoUsuarios.findAll(); // consola de spring
		System.out.println("Listado de Usuarios: ");
		usuarios.forEach(t -> {
			System.out.println(t);
		});
		return repoUsuarios.findAll(); // postman
	}

	public void guardar(Usuario usuario) {
		System.out.println("------------------------------------------------------------");
		repoUsuarios.save(usuario);
		System.out.println("Guardando " + usuario);
	}
	
	public void eliminar(int idUsuario) {
		System.out.println("Eliminando registro: " + buscarUsuario(idUsuario));
		repoUsuarios.deleteById(idUsuario);
	}
	
	public Optional<Usuario> buscarUsuario(int idUsuario) {
		System.out.println("------------------------------------------------------------");
		Optional<Usuario> optional = repoUsuarios.findById(idUsuario);
		if (optional.isPresent()) {
			Usuario u = optional.get();
			System.out.println("Elegiste " + u);
			return repoUsuarios.findById(idUsuario);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Usuario n° " + idUsuario);
		}
		return null;
	}
}
