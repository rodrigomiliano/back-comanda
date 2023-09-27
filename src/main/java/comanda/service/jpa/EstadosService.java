package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Estado;
import comanda.repository.EstadosRepository;
import comanda.service.IEstadosService;

@Service
public class EstadosService implements IEstadosService {

	@Autowired
	private EstadosRepository repoEstados;

	public List<Estado> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Estado> estados = repoEstados.findAll(); // Esto lo ordena en la consola de spring
		System.out.println("Listado de Estados: ");
		estados.forEach(t -> {
			System.out.println(t);
		});
		return repoEstados.findAll(); // postman
	}

	public void guardar(Estado estado) {
		System.out.println("------------------------------------------------------------");
		repoEstados.save(estado);
		System.out.println("Guardando " + estado);
	}

	public void eliminar(int idEstado) {
		System.out.println("Eliminando registro: " + buscarEstado(idEstado));
		repoEstados.deleteById(idEstado);
	}
	
	public Optional<Estado> buscarEstado(int idEstado) {
		System.out.println("------------------------------------------------------------");
		Optional<Estado> optional = repoEstados.findById(idEstado);
		if (optional.isPresent()) {
			Estado u = optional.get();
			System.out.println("Elegiste " + u);
			return repoEstados.findById(idEstado);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Estado n° " + idEstado);
		}
		return null;
	}
}
