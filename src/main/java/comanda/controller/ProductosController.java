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

import comanda.entity.Categoria;
import comanda.entity.Producto;
import comanda.service.IProductosService;

@RestController
@RequestMapping("/comanda")
public class ProductosController {

	@Autowired
	private IProductosService serviceProductos;
	
	@GetMapping("/cargar-productos")
	public void cargarProductos(){
		Producto p1 = new Producto("Producto 1", "Descripcion 1", 100.0, new Categoria(1));
		Producto p2 = new Producto("Producto 2", "Descripcion 2", 200.0, new Categoria(2));
		Producto p3 = new Producto("Producto 3", "Descripcion 3", 300.0, new Categoria(4));
		Producto p4 = new Producto("Producto 4", "Descripcion 4", 400.0, new Categoria(5));
		Producto p5 = new Producto("Producto 5", "Descripcion 5", 500.0, new Categoria(2));
		serviceProductos.guardar(p1);
		serviceProductos.guardar(p2);
		serviceProductos.guardar(p3);
		serviceProductos.guardar(p4);
		serviceProductos.guardar(p5);
	}
	
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
