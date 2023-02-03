package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Comanda;
import comanda.repository.ComandasRepository;
import comanda.service.IComandasService;

@Service
public class ComandasService implements IComandasService{

	@Autowired
	private ComandasRepository repoComandas;

	public List<Comanda> buscarTodas() {
		return repoComandas.findAll();
	}

	public void guardar(Comanda comanda) {
		repoComandas.save(comanda);
	}

	public void eliminar(int idComanda) {
		repoComandas.deleteById(idComanda);
	}
	
	public Optional<Comanda> buscarComanda(int idComanda) {
		return repoComandas.findById(idComanda);
	}
}
