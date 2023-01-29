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

import comanda.entity.Precio;
import comanda.service.IPreciosService;

@RestController
@RequestMapping("/comanda")
public class PreciosController {
	
	@Autowired
	private IPreciosService servicePrecios;
	
	@GetMapping("/precio")
	public List<Precio> buscarTodos(){
		return servicePrecios.buscarTodos();
	}
	
	@PostMapping("/precio") 
	public Precio guardar(@RequestBody Precio precio) {
		servicePrecios.guardar(precio);
		return precio;
	}
	
	@PutMapping("/precio")
	public Precio modificar(@RequestBody Precio precio) {
		servicePrecios.guardar(precio);
		return precio;
	} 
	
	@DeleteMapping("/precio/{id}")
	public String eliminar(@PathVariable("id") int idPrecio) {
		servicePrecios.eliminar(idPrecio);
		return "Registro Eliminado";
	}

}
