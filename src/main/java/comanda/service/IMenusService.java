package comanda.service;

import java.util.List;
import comanda.entity.Menu;

public interface IMenusService {

	List<Menu> buscarTodos();
	void guardar(Menu menu);
	void eliminar(int idMenu);
}
