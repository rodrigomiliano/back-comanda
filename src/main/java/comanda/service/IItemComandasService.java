package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.ItemComanda;

public interface IItemComandasService {

	List<ItemComanda> buscarTodos();
	void guardar(ItemComanda itemComanda);
	void eliminar(int idItemComanda);
	Optional<ItemComanda> buscarItemComanda(int idItemComanda);
}
