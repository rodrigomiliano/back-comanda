package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.ComItem;

public interface IComItemsService {

	List<ComItem> buscarTodos();
	void guardar(ComItem comItem);
	void eliminar(int idComItem);
	Optional<ComItem> buscarComItem(int idComItem);
}
