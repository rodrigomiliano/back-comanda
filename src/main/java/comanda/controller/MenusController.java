package comanda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import comanda.entity.Menu;
import comanda.service.IMenusService;


@RestController
@RequestMapping("/comanda")
public class MenusController {

	@Autowired
	private IMenusService serviceMenus;
	
	@GetMapping("/menu")
	public List<Menu> buscarTodos(){
		return serviceMenus.buscarTodos();
	}
	
	@PostMapping("/menu") 
	public Menu guardar(@RequestBody Menu menu) {
		serviceMenus.guardar(menu);
		return menu;
	}
	
	@PutMapping("/menu")
	public Menu modificar(@RequestBody Menu menu) {
		serviceMenus.guardar(menu);
		return menu;
	} 
	
	@DeleteMapping("/menu/{id}")
	public String eliminar(@PathVariable("id") int idMenu) {
		serviceMenus.eliminar(idMenu);
		return "Registro Eliminado";
	}

}
