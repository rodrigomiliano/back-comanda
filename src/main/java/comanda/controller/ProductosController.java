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

import comanda.entity.Producto;
import comanda.service.IProductosService;

@RestController
@RequestMapping("/comanda")
public class ProductosController {

	@Autowired
	private IProductosService serviceProductos;
	
	@GetMapping("/producto")
	public List<Producto> buscarTodos(){
		return serviceProductos.buscarTodos();
	}
	
	@PostMapping("/producto") 
	public Producto guardar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);
		return producto;
	}
	
	@PutMapping("/producto")
	public Producto modificar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);		
		return producto;
	} 
	
	@DeleteMapping("/producto/{id}")
	public String eliminar(@PathVariable("id") int idProducto) {
		serviceProductos.eliminar(idProducto);
		return "Registro Eliminado";
	}
}
