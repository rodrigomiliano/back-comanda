package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Moneda;
import comanda.repository.MonedasRepository;
import comanda.service.IMonedasService;

@Service
public class MonedasService implements IMonedasService{

	@Autowired
	private MonedasRepository repoMonedas;

	public List<Moneda> buscarTodas() {
		return repoMonedas.findAll();
	}

	public void guardar(Moneda moneda) {
		repoMonedas.save(moneda);
	}

	public void eliminar(int idMoneda) {
		repoMonedas.deleteById(idMoneda);
	}
	
	public Optional<Moneda> buscarMoneda(int idMoneda) {
		return repoMonedas.findById(idMoneda);
	}
}
