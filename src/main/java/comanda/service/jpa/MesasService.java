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
		System.out.println("------------------------------------------------------------");
		List<Mesa> mesas = repoMesas.findAll(); // Spring
		System.out.println("Listado de Mesas: ");
		mesas.forEach(t -> {
			System.out.println(t);
		});
		return repoMesas.findAll(); // Postman
	}

	public void guardar(Mesa mesa) {
		System.out.println("------------------------------------------------------------");
		repoMesas.save(mesa);
		System.out.println("Guardando " + mesa);
	}

	public void eliminar(int idMesa) {
		System.out.println("Eliminando registro: " + buscarMesa(idMesa));
		repoMesas.deleteById(idMesa);
	}

	public Optional<Mesa> buscarMesa(int idMesa) {
		System.out.println("------------------------------------------------------------");
		Optional<Mesa> mesa = repoMesas.findById(idMesa);
		System.out.println(mesa);
		return mesa;		
	}
}
