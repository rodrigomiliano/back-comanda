package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Comprobante;

public interface IComprobantesService {

	List<Comprobante> buscarTodos();
	void guardar(Comprobante comprobante);
	void eliminar(int idComprobante);
	Optional<Comprobante> buscarComprobante(int idComprobante);
}
