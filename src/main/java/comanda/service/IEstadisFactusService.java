package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.EstadisFactu;

public interface IEstadisFactusService {

	List<EstadisFactu> buscarTodas();
	void guardar(EstadisFactu estadisFactu);
	void eliminar(int idEstadisFactu);
	Optional<EstadisFactu> buscarEstadisFactu(int idEstadisFactu);
}
