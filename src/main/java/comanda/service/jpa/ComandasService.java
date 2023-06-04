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
		System.out.println("------------------------------------------------------------");
		List<Comanda> comandas = repoComandas.findAll(); // spring
		System.out.println("Listado de Comandas: ");
		comandas.forEach(t -> {
			System.out.println(t);
		});
		return repoComandas.findAll(); // postman
	}		

	public void guardar(Comanda comanda) {
		System.out.println("------------------------------------------------------------");
		repoComandas.save(comanda);
		System.out.println("Guardando " + comanda);
	}
		
	public void eliminar(int idComanda) {
		System.out.println("Eliminando registro: " + buscarComanda(idComanda));
		repoComandas.deleteById(idComanda);
	}	
		
	public Optional<Comanda> buscarComanda(int idComanda) {
		System.out.println("------------------------------------------------------------");
		Optional<Comanda> optional = repoComandas.findById(idComanda);
		if (optional.isPresent()) {
			Comanda u = optional.get();
			System.out.println("Elegiste " + u);
			return repoComandas.findById(idComanda);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe la Comanda n° " + idComanda);
		}
		return null;
	}
		
}
