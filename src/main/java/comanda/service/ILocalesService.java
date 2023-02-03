package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Local;

public interface ILocalesService {

	List<Local> buscarTodos();
	void guardar(Local local);
	void eliminar(int idLocal);
	Optional<Local> buscarLocal(int idLocal);
}
