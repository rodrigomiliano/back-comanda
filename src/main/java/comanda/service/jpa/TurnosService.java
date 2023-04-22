package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Turno;
import comanda.repository.TurnosRepository;
import comanda.service.ITurnosService;

@Service
public class TurnosService implements ITurnosService {

	@Autowired
	private TurnosRepository repoTurnos;

	public List<Turno> buscarTodos() {
		return repoTurnos.findAll();
	}

	public void guardar(Turno turno) {
		repoTurnos.save(turno);
	}

	public void eliminar(int idTurno) {
		repoTurnos.deleteById(idTurno);
	}
	
	public Optional<Turno> buscarTurno(int idTurno) {
		return repoTurnos.findById(idTurno);
	}
}
