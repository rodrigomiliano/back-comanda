package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.MenuItem;
import comanda.repository.MenuItemsRepository;
import comanda.service.IMenuItemsService;

@Service
public class MenuItemsService implements IMenuItemsService {

	@Autowired
	private MenuItemsRepository repoMenuItems;
	
	public List<MenuItem> buscarTodos() {
		return repoMenuItems.findAll();
	}

	public void guardar(MenuItem menuItem) {
		repoMenuItems.save(menuItem);
	}

	public void eliminar(int idMenuItem) {
		repoMenuItems.deleteById(idMenuItem);
	}
	
	public Optional<MenuItem> buscarMenuItem(int idMenuItem) {
		return repoMenuItems.findById(idMenuItem);
	}
}
