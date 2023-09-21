package comanda.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comanda.controller.dto.request.ProductoUpdateDto;
import comanda.entity.Categoria;
import comanda.entity.Producto;
import comanda.service.ComandaServiceException;
import comanda.service.IProductosService;

@RestController
@RequestMapping("/comanda")
public class ProductosController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

	@Autowired
	private IProductosService serviceProductos;

	@GetMapping("/producto")
	public List<Producto> buscarTodos() {
		return serviceProductos.buscarTodos();
	}

	@GetMapping("/producto/{id}")
	public Producto buscarProducto(@PathVariable("id") int idProducto) {
		Producto producto = null;
		try {
			producto = serviceProductos.buscarProducto(idProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}

	@PostMapping("/producto")
	public Producto guardar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);
		// se podria hacer un find del producto enviado antes de mostrar, esto para no
		// ver el nombre null
		return producto;
		// return Optional<Producto> buscarProducto(0);
	}

	@PutMapping("/producto")
	public Producto modificar(@RequestBody Producto producto) {
		serviceProductos.guardar(producto);
		// se podria pasar el id por url (como en delete)
		return producto;
	}

	@PutMapping("/producto/{id}")
	public Producto modificar(@PathVariable("id") int idProducto, @RequestBody ProductoUpdateDto producto) {

		LOGGER.info("idProducto: " + idProducto);
		LOGGER.info("Producto: " + producto.toString());
		Producto prod = null;
		try {
			prod = serviceProductos.buscarProducto(idProducto);
		} catch (ComandaServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reemplazo el valor del objeto actualmente en la DB con el valor que se pasa
		// por el Body
		prod.setPrecio(producto.getPrecio());
		LOGGER.info("Categoria actual: " + prod.getCategoria());
		LOGGER.info("Categoria nueva: " + producto.getCategoriaId());
		// Categoria nuevaCategoria =
		// categoriaService.buscarPorId(producto.getCategoriaId());
		// prod.setCategoria(nuevaCategoria );

		prod.setNombre(producto.getNombre());

		LOGGER.info("prod: " + prod.toString());

		serviceProductos.guardar(prod);
		LOGGER.info("Producto guardado: " + prod.toString());

		return prod;
	}

	@DeleteMapping("/producto/{id}")
	public String eliminar(@PathVariable("id") int idProducto) {
		try {
			serviceProductos.eliminar(idProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Registro Eliminado";
	}
}
