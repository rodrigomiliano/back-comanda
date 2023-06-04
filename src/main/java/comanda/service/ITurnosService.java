package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Turno;

public interface ITurnosService {

	List<Turno> buscarTodos();
	void guardar(Turno turno);
	void eliminar(int idTurno);
	Optional<Turno> buscarTurno(int idTurno);
}
