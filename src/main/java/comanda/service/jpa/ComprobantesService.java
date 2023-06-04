package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Comprobante;
import comanda.repository.ComprobantesRepository;
import comanda.service.IComprobantesService;

@Service
public class ComprobantesService implements IComprobantesService {

	@Autowired
	private ComprobantesRepository repoComprobantes;

	public List<Comprobante> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Comprobante> comprobantes = repoComprobantes.findAll(); // spring
		System.out.println("Listado de Comprobantes: ");
		comprobantes.forEach(t -> {
			System.out.println(t);
		});
		return repoComprobantes.findAll(); // postman
	}
		
	public void guardar(Comprobante comprobante) {
		System.out.println("------------------------------------------------------------");
		repoComprobantes.save(comprobante);
		System.out.println("Guardando " + comprobante);
	}
		
	public void eliminar(int idComprobante) {
		System.out.println("Eliminando registro: " + buscarComprobante(idComprobante));
		repoComprobantes.deleteById(idComprobante);
	}
		
	public Optional<Comprobante> buscarComprobante(int idComprobante) {
		System.out.println("------------------------------------------------------------");
		Optional<Comprobante> optional = repoComprobantes.findById(idComprobante);
		if (optional.isPresent()) {
			Comprobante u = optional.get();
			System.out.println("Elegiste " + u);
			return repoComprobantes.findById(idComprobante);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Comprobante n° " + idComprobante);
		}
		return null;
	}	
	
}
