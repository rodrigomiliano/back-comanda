package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Estado;

public interface IEstadosService {

	List<Estado> buscarTodos();
	void guardar(Estado estado);
	void eliminar(int idEstado);
	Optional<Estado> buscarEstado(int idEstado);
}
