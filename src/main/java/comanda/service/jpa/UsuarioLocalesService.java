package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.UsuarioLocal;
import comanda.repository.UsuarioLocalesRepository;
import comanda.service.IUsuarioLocalesService;

@Service
public class UsuarioLocalesService implements IUsuarioLocalesService{

	@Autowired
	private UsuarioLocalesRepository repoUsuarioLocales;

	public List<UsuarioLocal> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<UsuarioLocal> userloc = repoUsuarioLocales.findAll(); // consola de spring
		System.out.println("Listado de Usuarios - Locales: ");
		userloc.forEach(t -> {
			System.out.println(t);
		});
		return repoUsuarioLocales.findAll(); // postman
	}
	
	public void guardar(UsuarioLocal userloc) {
		System.out.println("------------------------------------------------------------");
		repoUsuarioLocales.save(userloc);
		System.out.println("Guardando " + userloc);
	}
	
	public void eliminar(int idUsuarioLocal) {
		System.out.println("Eliminando registro: " + buscarUsuarioLocal(idUsuarioLocal));
		repoUsuarioLocales.deleteById(idUsuarioLocal);
	}
	
	public Optional<UsuarioLocal> buscarUsuarioLocal(int idUsuarioLocal) {
		System.out.println("------------------------------------------------------------");
		Optional<UsuarioLocal> optional = repoUsuarioLocales.findById(idUsuarioLocal);
		if (optional.isPresent()) {
			UsuarioLocal u = optional.get();
			System.out.println("Elegiste " + u);
			return repoUsuarioLocales.findById(idUsuarioLocal);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Usuario - Local n° " + idUsuarioLocal);
		}
		return null;
	}
	
}
