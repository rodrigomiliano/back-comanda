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
import comanda.entity.MenuItem;
import comanda.service.IMenuItemsService;
import comanda.service.IMenusService;

@RestController
@RequestMapping("/comanda")
public class MenuItemsController {

	@Autowired
	private IMenuItemsService serviceMenuItems;
	
	@GetMapping("/menuItem")
	public List<MenuItem> buscarTodos(){
		return serviceMenuItems.buscarTodos();
	}
	
	@PostMapping("/menuItem") 
	public MenuItem guardar(@RequestBody MenuItem menuItem) {
		serviceMenuItems.guardar(menuItem);
		return menuItem;
	}
	
	@PutMapping("/menuItem")
	public MenuItem modificar(@RequestBody MenuItem menuItem) {
		serviceMenuItems.guardar(menuItem);
		return menuItem;
	} 
	
	@DeleteMapping("/menuItem/{id}")
	public String eliminar(@PathVariable("id") int idMenuItem) {
		serviceMenuItems.eliminar(idMenuItem);
		return "Registro Eliminado";
	}
}
