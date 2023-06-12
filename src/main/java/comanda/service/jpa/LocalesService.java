package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Local;
import comanda.entity.Rol;
import comanda.repository.LocalesRepository;
import comanda.service.ILocalesService;

@Service
public class LocalesService implements ILocalesService {

	@Autowired
	private LocalesRepository repoLocales;
	
	public List<Local> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Local> locales = repoLocales.findAll(); // consola de spring
		System.out.println("Listado de Locales: ");
		locales.forEach(t -> {
			System.out.println(t);
		});
		return repoLocales.findAll(); // postman
	}
	
	public void guardar(Local local) {
		System.out.println("------------------------------------------------------------");
		repoLocales.save(local);
		System.out.println("Guardando " + local);
	}

	public void eliminar(int idLocal) {
		System.out.println("Eliminando registro: " + buscarLocal(idLocal));
		repoLocales.deleteById(idLocal);
	}
	
	public Optional<Local> buscarLocal(int idLocal) {
		System.out.println("------------------------------------------------------------");
		Optional<Local> optional = repoLocales.findById(idLocal);
		if (optional.isPresent()) {
			Local u = optional.get();
			System.out.println("Elegiste " + u);
			return repoLocales.findById(idLocal);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Local n° " + idLocal);
		}
		return null;
	}
}
