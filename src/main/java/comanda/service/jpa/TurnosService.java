package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comanda.entity.Rol;
import comanda.entity.Turno;
import comanda.repository.TurnosRepository;
import comanda.service.ITurnosService;

@Service
public class TurnosService implements ITurnosService {

	@Autowired
	private TurnosRepository repoTurnos;
	
	public List<Turno> buscarTodos() {
		System.out.println("------------------------------------------------------------");
		List<Turno> turnos = repoTurnos.findAll(); // consola de spring
		System.out.println("Listado de Turnos: ");
		turnos.forEach(t -> {
			System.out.println(t);
		});
		return repoTurnos.findAll(); // postman
	}
	
	public void guardar(Turno turno) {
		System.out.println("------------------------------------------------------------");
		repoTurnos.save(turno);
		System.out.println("Guardando " + turno);
	}

	public void eliminar(int idTurno) {
		System.out.println("Eliminando registro: " + buscarTurno(idTurno));
		repoTurnos.deleteById(idTurno);
	}
	
	public Optional<Turno> buscarTurno(int idTurno) {
		System.out.println("------------------------------------------------------------");
		Optional<Turno> optional = repoTurnos.findById(idTurno);
		if (optional.isPresent()) {
			Turno u = optional.get();
			System.out.println("Elegiste " + u);
			return repoTurnos.findById(idTurno);
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("No existe el Turno n° " + idTurno);
		}
		return null;
	}
}
