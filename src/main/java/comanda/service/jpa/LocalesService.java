package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Local;
import comanda.repository.LocalesRepository;
import comanda.service.ILocalesService;

@Service
public class LocalesService implements ILocalesService {

	@Autowired
	private LocalesRepository repoLocales;

	public List<Local> buscarTodos() {
		return repoLocales.findAll();
	}

	public void guardar(Local local) {
		repoLocales.save(local);
	}

	public void eliminar(int idLocal) {
		repoLocales.deleteById(idLocal);
	}
	
	public Optional<Local> buscarLocal(int idLocal) {
		return repoLocales.findById(idLocal);
	}
}
