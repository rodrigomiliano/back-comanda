package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Mesa;
import comanda.repository.MesasRepository;
import comanda.service.IMesasService;

@Service
public class MesasService implements IMesasService {

	@Autowired
	private MesasRepository repoMesas;

	public List<Mesa> buscarTodas() {
		List<Mesa> mesas = repoMesas.findAll();
		mesas.forEach(t -> {
			System.out.println(t);
		});
		return repoMesas.findAll();
	}

	public void guardar(Mesa mesa) {
		repoMesas.save(mesa);
	}

	public void eliminar(int idMesa) {
		repoMesas.deleteById(idMesa);
	}

	public Optional<Mesa> buscarMesa(int idMesa) {
		Optional<Mesa> mesa = repoMesas.findById(idMesa);
		System.out.println(mesa);
		return mesa;
		
	}
}
