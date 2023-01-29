package comanda.service;

import java.util.List;
import comanda.entity.MenuItem;

public interface IMenuItemsService {
	
	List<MenuItem> buscarTodos();
	void guardar(MenuItem menuItem);
	void eliminar(int idMenuItem);
}
