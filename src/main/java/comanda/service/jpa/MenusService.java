package comanda.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comanda.entity.Menu;
import comanda.repository.MenusRepository;
import comanda.service.IMenusService;

@Service
public class MenusService implements IMenusService {

	@Autowired
	private MenusRepository repoMenus;
	
	public List<Menu> buscarTodos() {
		return repoMenus.findAll();
	}

	public void guardar(Menu menu) {
		repoMenus.save(menu);
	}

	public void eliminar(int idMenu) {
		repoMenus.deleteById(idMenu);
	}
	
	public Optional<Menu> buscarMenu(int idMenu) {
		return repoMenus.findById(idMenu);
	}

}
