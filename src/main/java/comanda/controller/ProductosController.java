package comanda.controller;

import java.util.List;
import java.util.Optional;
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
	
	@GetMapping("/producto/{id}")
	public Optional<Producto> buscarProducto(@PathVariable("id") int idProducto) {
		return serviceProductos.buscarProducto(idProducto);
	}
	
	@PostMapping("/producto") 
	public Producto guardar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);
		// se podria hacer un find del producto enviado antes de mostrar, esto para no ver el nombre null
		return producto;
		//return Optional<Producto> buscarProducto(0);
	}
	
	@PutMapping("/producto")
	public Producto modificar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);
		// se podria pasar el id por url (como en delete)
		return producto;
	} 		
		
	@DeleteMapping("/producto/{id}")
	public String eliminar(@PathVariable("id") int idProducto) {
		serviceProductos.eliminar(idProducto);
		return "Registro Eliminado";
	}
}
