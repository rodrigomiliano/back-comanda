package comanda.service;

import java.util.List;
import java.util.Optional;
import comanda.entity.Menu;

public interface IMenusService {

	List<Menu> buscarTodos();
	void guardar(Menu menu);
	void eliminar(int idMenu);
	Optional<Menu> buscarMenu(int idMenu);
}
