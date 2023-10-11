package comanda.service;

import java.util.List;
import comanda.entity.Turno;

public interface ITurnosService {

	List<Turno> buscarTodos();
	void guardar(Turno turno);
	void eliminar(int idTurno) throws Exception;
	Turno buscarTurno(int idTurno) throws ComandaServiceException;
}
