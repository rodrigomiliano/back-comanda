package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Precio;
import comanda.repository.PreciosRepository;
import comanda.service.IPreciosService;

@Service
public class PreciosService implements IPreciosService {

	@Autowired
	private PreciosRepository repoPrecios;
	
	public List<Precio> buscarTodos() {
		return repoPrecios.findAll();
	}

	public void guardar(Precio precio) {
		repoPrecios.save(precio);
	}

	public void eliminar(int idPrecio) {
		repoPrecios.deleteById(idPrecio);
	}
	
	public Optional<Precio> buscarPrecio(int idPrecio) {
		return repoPrecios.findById(idPrecio);
	}
}
