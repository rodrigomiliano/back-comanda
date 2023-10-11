package comanda.service;

import java.util.List;
import comanda.entity.Estado;

public interface IEstadosService {

	List<Estado> buscarTodos();
	void guardar(Estado estado);
	void eliminar(int idEstado) throws Exception;
	Estado buscarEstado(int idEstado) throws ComandaServiceException;
}
