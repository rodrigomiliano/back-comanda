package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.ItemComprobante;

public interface IItemComprobantesService {

	List<ItemComprobante> buscarTodos();
	void guardar(ItemComprobante itemComprobante);
	void eliminar(int idItemComprobante);
	Optional<ItemComprobante> buscarItemComprobante(int idItemComprobante);
}
